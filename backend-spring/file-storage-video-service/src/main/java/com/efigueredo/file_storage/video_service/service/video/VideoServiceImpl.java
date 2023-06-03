package com.efigueredo.file_storage.video_service.service.video;

import com.efigueredo.file_storage.shared.service.ExecutorFuncaoQuandoPastaExistirFlux;
import com.efigueredo.file_storage.shared.service.ExecutorFuncaoQuandoPastaExistirMono;
import com.efigueredo.file_storage.shared.service.ServicesUtils;
import com.efigueredo.file_storage.shared.service.files.TrocadorNomeService;
import com.efigueredo.file_storage.shared.service.files.VerificadorService;
import com.efigueredo.file_storage.shared.service.pastas.PastaService;
import com.efigueredo.file_storage.video_service.domain.Video;
import com.efigueredo.file_storage.video_service.domain.VideoRepository;
import com.efigueredo.file_storage.shared.domain.FileStorageArquivo;
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
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VerificadorService verificadorVideos;

    @Autowired
    private TrocadorNomeService trocadorNomeVideos;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ServicesUtils<Video> servicesUtilsVideo;

    @Autowired
    private ServicesUtils<Void> servicesUtilsVoid;

    @Autowired
    private PastaRepository pastaRepository;

    @Autowired
    private PastaService pastaService;

    private final long idUsuarioLogado;

    public VideoServiceImpl() {
        UsuarioLogado usuarioLogado = new UsuarioLogadoImpl();
        this.idUsuarioLogado = usuarioLogado.obterIdUsuarioLogado();
    }

    @Override
    public Flux<Video> listarVideosDoUsuario(String idPasta) {
        return this.pastaRepository.existsByIdUsuarioAndId(this.idUsuarioLogado, idPasta)
                .flatMapMany(Flux::just)
                .flatMap(pastaExiste -> this.servicesUtilsVideo.executarQuandoPastaExistirFlux(pastaExiste, idPasta,
                        () -> this.videoRepository
                                .findAllByIdUsuarioAndIdPastaOrderByMomentoUploadDesc(this.idUsuarioLogado, idPasta)));

    }

    @Override
    public Mono<FileStorageArquivo> uploadFile(FileStorageDto dto) {
        return Mono.just(dto)
                .flatMap(this.trocadorNomeVideos::trocarNomeCasoJaExista)
                .flatMap(this::verificarSePastaExiste)
                .flatMap(this::salvarVideoNoBancoDeDados)
                .flatMap(this.pastaService::aumentarTamanhoDaPasta)
                .flatMap(this.pastaService::contabilizarAdicaoArquivoNaPasta);
    }

    @Override
    public Mono<FileStorageArquivo> alterarNomeFileDoUsuario(String idFile, String novoNome) {
        return this.verificadorVideos.lancarExcecaoQuandoFileDeIdNaoExistir(idFile)
                .flatMap(id -> this.videoRepository.findByIdAndIdUsuario(idFile, this.idUsuarioLogado))
                .zipWhen(video -> this.obterDtoUpladComNomeAdequado(video, novoNome))
                .flatMap(this::trocarNomeVideo);
    }

    @Override
    public Mono<FileStorageArquivo> removerFileDoUsuario(FileStorageArquivo video) {
        return Mono.just((Video) video)
                .flatMap(video1 -> this.videoRepository.delete(video1))
                .then(Mono.just(video))
                .flatMap(this.pastaService::diminuirTamanhoDaPasta)
                .flatMap(this.pastaService::contabilizarRemocaoArquivoNaPasta);
    }

    @Override
    public Mono<String> removerTodosFilesDaPasta(String nome) {
        return this.pastaService.removerTodosArquivosDaPasta(nome)
                .flatMap(pasta -> this.videoRepository.deleteAllByIdUsuarioAndIdPasta(this.idUsuarioLogado, pasta.getId()))
                .then(Mono.just(nome));
    }

    @Override
    public Mono<FileStorageArquivo> obterFileDoUsuario(String idVideo) {
        return this.verificadorVideos.lancarExcecaoQuandoFileDeIdNaoExistir(idVideo)
                .flatMap(video -> this.videoRepository.findByIdAndIdUsuario(idVideo, this.idUsuarioLogado));
    }

    @Override
    public Mono<Void> inverterValorFavoritoFile(String idFile) {
        return this.verificadorVideos.lancarExcecaoQuandoFileDeIdNaoExistir(idFile)
                .flatMap(id -> this.videoRepository.findByIdAndIdUsuario(idFile, this.idUsuarioLogado))
                .flatMap(video -> {
                    video.inverterFavorito();
                    return this.videoRepository.save(video);
                })
                .then(Mono.empty());
    }

    private Mono<Video> salvarVideoNoBancoDeDados(FileStorageDto dto) {
        Video video = this.modelMapper.map(dto, Video.class);
        video.setFavorito(false);
        video.setIdUsuario(this.idUsuarioLogado);
        video.setMomentoUpload(LocalDateTime.now());
        video.setId(null);
        return this.videoRepository.save(video);
    }

    private Mono<Video> trocarNomeVideo(Tuple2<Video, FileStorageDto> tuple) {
        Video video = tuple.getT1();
        FileStorageDto dados = tuple.getT2();
        if(this.nomePodeSerTrocado(video, dados)) {
            video.setNome(dados.getNome());
            return this.videoRepository.save(video);
        };
        return Mono.just(video) ;
    }

    private boolean nomePodeSerTrocado(Video video, FileStorageDto dados) {
        String nomeCompletoVideo = video.getNome();
        String nomeCompletoDados = dados.getNome();
        String nomeVideo = nomeCompletoVideo.substring(0, nomeCompletoVideo.lastIndexOf(".") - 3);
        String nomeDados = nomeCompletoDados.substring(0, nomeCompletoDados.lastIndexOf(".") - 3);
        return !nomeVideo.equals(nomeDados);
    }

    private Mono<FileStorageDto> obterDtoUpladComNomeAdequado(Video video, String novoNome) {
        FileStorageDto dto = this.modelMapper.map(video, FileStorageDto.class);
        dto.setNome(novoNome);
        return this.trocadorNomeVideos.trocarNomeCasoJaExista(dto);
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
