package com.efigueredo.file_storage.shared.service.pastas;

import com.efigueredo.file_storage.shared.domain.Arquivo;
import com.efigueredo.file_storage.shared.domain.Pasta;
import com.efigueredo.file_storage.shared.domain.PastaRepository;
import com.efigueredo.file_storage.shared.service.usuarios.UsuarioLogadoImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@Slf4j
public class PastaServiceImpl implements PastaService {

    @Autowired
    protected PastaRepository pastaRepository;

    @Autowired
    protected VerificadorPastas verificadorPastas;

    private final long idUsuarioLogado;

    public PastaServiceImpl() {
        this.idUsuarioLogado = new UsuarioLogadoImpl().obterIdUsuarioLogado();
    }

    @Override
    public Mono<Pasta> criarPasta(String nomePasta) {
        return Mono.just(nomePasta)
                .doOnNext(System.out::println)
                .map(nome -> new Pasta(null, this.idUsuarioLogado, nome, false, 0, 0L))
                .flatMap(this.pastaRepository::insert)
                .doOnNext(System.out::println);
    }

    @Override
    public Mono<String> removerPasta(String nomePasta) {
        return this.verificadorPastas.lancarExcecaoQuandoPastaDeUsuarioNaoExistirNome(idUsuarioLogado, nomePasta)
                .flatMap(nome -> this.pastaRepository.findByIdUsuarioAndNome(this.idUsuarioLogado, nomePasta))
                .flatMap(this.pastaRepository::delete)
                .then(Mono.just(nomePasta));
    }

    @Override
    public Mono<Pasta> atualizarPasta(String nomePasta, String novoNome) {
        return this.verificadorPastas.lancarExcecaoQuandoPastaDeUsuarioExistirNome(this.idUsuarioLogado, novoNome)
                .flatMap(novoNomeVerificado -> this.verificadorPastas
                        .lancarExcecaoQuandoPastaDeUsuarioNaoExistirNome(idUsuarioLogado, nomePasta))
                .flatMap(nome -> this.pastaRepository.findByIdUsuarioAndNome(idUsuarioLogado, nomePasta))
                .doOnNext(pasta -> pasta.setNome(novoNome))
                .flatMap(this.pastaRepository::save);
    }

    @Override
    public Flux<Pasta> listarPastasDoUsuario(int size, int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "favorito");
        return this.pastaRepository.findAllByIdUsuario(this.idUsuarioLogado, sort);
    }

    @Override
    public Mono<Pasta> obterPastaDoUsuario(Mono<String> idPasta) {
        return idPasta
                .flatMap(id -> this.verificadorPastas.lancarExcecaoQuandoPastaDeUsuarioNaoExistirId(this.idUsuarioLogado, id))
                .flatMap(id -> this.pastaRepository.findByIdUsuarioAndId(this.idUsuarioLogado, id));
    }

    @Override
    public Mono<Void> mudarEstadoFavorito(Mono<String> idPasta) {
        return idPasta
                .flatMap(id -> this.verificadorPastas.lancarExcecaoQuandoPastaDeUsuarioNaoExistirId(this.idUsuarioLogado, id))
                .flatMap(id -> this.pastaRepository.findByIdUsuarioAndId(this.idUsuarioLogado, id))
                .flatMap(pasta -> {
                    pasta.trocarFavorito();
                    return this.pastaRepository.save(pasta);
                })
                .then(Mono.empty());
    }

    @Override
    public Mono<Arquivo> aumentarTamanhoDaPasta(Arquivo file) {
        return this.obterPastaParaFile(file)
                .flatMap(pasta -> {
                    pasta.contabilizarSomaTamanho(file.getTamanho());
                    return this.pastaRepository.save(pasta);
                })
                .then(Mono.just(file));
    }

    @Override
    public Mono<Arquivo> diminuirTamanhoDaPasta(Arquivo file) {
       return this.obterPastaParaFile(file)
                .flatMap(pasta -> {
                    pasta.contabilizarSubtracaoTamanho(file.getTamanho());
                    return this.pastaRepository.save(pasta);
                })
               .then(Mono.just(file));
    }

    @Override
    public Mono<Arquivo> contabilizarAdicaoArquivoNaPasta(Arquivo file) {
        return this.obterPastaParaFile(file)
                .flatMap(pasta -> {
                    pasta.somarNovoArquivo();
                    return this.pastaRepository.save(pasta);
                })
                .then(Mono.just(file));
    }

    @Override
    public Mono<Arquivo> contabilizarRemocaoArquivoNaPasta(Arquivo file) {
        return this.obterPastaParaFile(file)
                .flatMap(pasta -> {
                    pasta.subtrairRemocaoArquivo();
                    return this.pastaRepository.save(pasta);
                })
                .then(Mono.just(file));
    }

    @Override
    public Mono<Pasta> removerTodosArquivosDaPasta(String idPasta) {
        return this.obterPastaDoUsuario(Mono.just(idPasta))
                .flatMap(pasta -> {
                    pasta.zerarPasta();
                    return this.pastaRepository.save(pasta);
                });
    }

    private Mono<Pasta> obterPastaParaFile(Arquivo file) {
        return Mono.just(file)
                .flatMap(file1 -> this.verificadorPastas
                        .lancarExcecaoQuandoPastaDeUsuarioNaoExistirId(this.idUsuarioLogado, file.getIdPasta()))
                .flatMap(id -> this.pastaRepository.findByIdUsuarioAndId(this.idUsuarioLogado, id));
    }

}
