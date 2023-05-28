package com.efigueredo.file_storage.video_service.service.pastas;

import com.efigueredo.file_storage.video_service.domain.Pasta;
import com.efigueredo.file_storage.video_service.domain.PastaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class PastaService {

    @Autowired
    protected PastaRepository pastaRepository;

    @Autowired
    protected VerificadorPastas verificadorPastas;

    public abstract Mono<Pasta> criarPasta(Mono<String> nomePasta);
    public abstract Mono<Pasta> removerPasta(Mono<String>  idPasta);
    public abstract Mono<Pasta> atualizarPasta(Mono<String>  idPasta, String novoNome);
    public abstract Flux<Pasta> listarPastasDoUsuario(int size, int page);
    public abstract Mono<Pasta> obterPastaDoUsuario(Mono<String>  idPasta);
    public abstract Mono<Void> mudarEstadoFavorito(Mono<String>  idPasta);

}
