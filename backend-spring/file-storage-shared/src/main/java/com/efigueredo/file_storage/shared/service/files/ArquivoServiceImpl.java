package com.efigueredo.file_storage.shared.service.files;

import com.efigueredo.file_storage.shared.domain.Arquivo;
import com.efigueredo.file_storage.shared.domain.ArquivoRepository;
import com.efigueredo.file_storage.shared.service.ServicesUtils;
import com.efigueredo.file_storage.shared.service.pastas.PastaService;
import com.efigueredo.file_storage.shared.domain.PastaRepository;
import com.efigueredo.file_storage.shared.service.dto.FileStorageDto;
import com.efigueredo.file_storage.shared.service.usuarios.UsuarioLogado;
import com.efigueredo.file_storage.shared.service.usuarios.UsuarioLogadoImpl;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import com.efigueredo.file_storage.shared.infra.exception.FileStorageException;

import java.time.LocalDateTime;

@Slf4j
@Transactional
@Service
public class ArquivoServiceImpl implements ArquivoService {

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private VerificadorService verificadorService;

    @Autowired
    private TrocadorNomeService trocadorNomeService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ServicesUtils<Arquivo> servicesUtilsArquivo;

    @Autowired
    private ServicesUtils<Void> servicesUtilsVoid;

    @Autowired
    private PastaRepository pastaRepository;

    @Autowired
    private PastaService pastaService;

    private final long idUsuarioLogado;

    public ArquivoServiceImpl() {
        UsuarioLogado usuarioLogado = new UsuarioLogadoImpl();
        this.idUsuarioLogado = usuarioLogado.obterIdUsuarioLogado();
    }

    @Override
    public Flux<Arquivo> listarArquivosDoUsuario(String idPasta) {
        return this.pastaRepository.existsByIdUsuarioAndId(this.idUsuarioLogado, idPasta)
                .flatMapMany(Flux::just)
                .flatMap(pastaExiste -> this.servicesUtilsArquivo.executarQuandoPastaExistirFlux(pastaExiste, idPasta,
                        () -> this.arquivoRepository
                                .findAllByIdUsuarioAndIdPastaOrderByMomentoUploadDesc(this.idUsuarioLogado, idPasta)));
    }

    @Override
    public Mono<Arquivo> uploadArquivo(FileStorageDto dto) {
        return Mono.just(dto)
                .flatMap(this.trocadorNomeService::trocarNomeCasoJaExista)
                .flatMap(this::verificarSePastaExiste)
                .flatMap(this::salvarArquivoNoBancoDeDados)
                .flatMap(this.pastaService::aumentarTamanhoDaPasta)
                .flatMap(this.pastaService::contabilizarAdicaoArquivoNaPasta);
    }

    @Override
    public Mono<Arquivo> alterarNomeArquivoDoUsuario(String idFile, String novoNome) {
        return this.verificadorService.lancarExcecaoQuandoFileDeIdNaoExistir(idFile)
                .flatMap(id -> this.arquivoRepository.findByIdAndIdUsuario(idFile, this.idUsuarioLogado))
                .zipWhen(arquivo -> this.obterDtoUpladComNomeAdequado(arquivo, novoNome))
                .flatMap(this::trocarNomeArquivo);
    }

    @Override
    public Mono<Arquivo> removerArquivoDoUsuario(Arquivo arquivo) {
        return this.arquivoRepository.delete(arquivo)
                .then(Mono.just(arquivo))
                .flatMap(this.pastaService::diminuirTamanhoDaPasta)
                .flatMap(this.pastaService::contabilizarRemocaoArquivoNaPasta);
    }

    @Override
    public Mono<String> removerTodosOsArquivosDaPasta(String nome) {
        return this.pastaService.removerTodosArquivosDaPasta(nome)
                .flatMap(pasta -> this.arquivoRepository.deleteAllByIdUsuarioAndIdPasta(this.idUsuarioLogado, pasta.getId()))
                .then(Mono.just(nome));
    }

    @Override
    public Mono<Arquivo> obterArquivoDoUsuario(String idVideo) {
        return this.verificadorService.lancarExcecaoQuandoFileDeIdNaoExistir(idVideo)
                .flatMap(video -> this.arquivoRepository.findByIdAndIdUsuario(idVideo, this.idUsuarioLogado));
    }

    @Override
    public Mono<Void> inverterValorFavoritoFile(String idFile) {
        return this.verificadorService.lancarExcecaoQuandoFileDeIdNaoExistir(idFile)
                .flatMap(id -> this.arquivoRepository.findByIdAndIdUsuario(idFile, this.idUsuarioLogado))
                .flatMap(arquivo -> {
                    arquivo.inverterFavorito();
                    return this.arquivoRepository.save(arquivo);
                })
                .then(Mono.empty());
    }

    private Mono<Arquivo> salvarArquivoNoBancoDeDados(FileStorageDto dto) {
        Arquivo arquivo = this.modelMapper.map(dto, Arquivo.class);
        arquivo.setFavorito(false);
        arquivo.setIdUsuario(this.idUsuarioLogado);
        arquivo.setMomentoUpload(LocalDateTime.now());
        arquivo.setId(null);
        return this.arquivoRepository.save(arquivo);
    }

    private Mono<Arquivo> trocarNomeArquivo(Tuple2<Arquivo, FileStorageDto> tuple) {
        Arquivo arquivo = tuple.getT1();
        FileStorageDto dados = tuple.getT2();
        if(this.nomePodeSerTrocado(arquivo, dados)) {
            arquivo.setNome(dados.getNome());
            return this.arquivoRepository.save(arquivo);
        };
        return Mono.just(arquivo) ;
    }

    private boolean nomePodeSerTrocado(Arquivo arquivo, FileStorageDto dados) {
        String nomeCompletoArquivo = arquivo.getNome();
        String nomeCompletoDados = dados.getNome();
        String nomeArquivo = nomeCompletoArquivo.substring(0, nomeCompletoArquivo.lastIndexOf(".") - 3);
        String nomeDados = nomeCompletoDados.substring(0, nomeCompletoDados.lastIndexOf(".") - 3);
        return !nomeArquivo.equals(nomeDados);
    }

    private Mono<FileStorageDto> obterDtoUpladComNomeAdequado(Arquivo video, String novoNome) {
        FileStorageDto dto = this.modelMapper.map(video, FileStorageDto.class);
        dto.setNome(novoNome);
        return this.trocadorNomeService.trocarNomeCasoJaExista(dto);
    }

    private Mono<FileStorageDto> verificarSePastaExiste(FileStorageDto dto) {
        return this.pastaRepository.existsByIdUsuarioAndId(this.idUsuarioLogado, dto.getIdPasta())
                .flatMap(pastaExiste -> this.tratarErroQuandoPastaNaoExistir(pastaExiste, dto));
    }

    private Mono<FileStorageDto> tratarErroQuandoPastaNaoExistir(Boolean pastaExiste, FileStorageDto dto) {
        if(pastaExiste) {
            return Mono.just(dto);
        }
        throw new FileStorageException("Pasta não encontrada", "A pasta de id "
                + dto.getIdPasta() + " não existe para o usuário de id "
                + this.idUsuarioLogado, "", 404);
    }

}
