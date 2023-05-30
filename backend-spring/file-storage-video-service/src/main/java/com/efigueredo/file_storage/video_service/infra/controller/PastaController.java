package com.efigueredo.file_storage.video_service.infra.controller;

import com.efigueredo.file_storage.video_service.domain.Pasta;
import com.efigueredo.file_storage.video_service.infra.excetion.FileStorageException;
import com.efigueredo.file_storage.video_service.service.pastas.PastaDiscoService;
import com.efigueredo.file_storage.video_service.service.pastas.PastaService;
import com.efigueredo.file_storage.video_service.service.pastas.VerificadorPastas;
import com.efigueredo.file_storage.video_service.service.usuarios.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("videos/pastas")
public class PastaController {

    @Autowired
    private PastaService pastaService;

    @Autowired
    private PastaDiscoService pastaDiscoService;

    @Autowired
    private VerificadorPastas verificadorPastas;

    @Autowired
    private UsuarioLogado usuarioLogado;

    @GetMapping
    public Flux<Pasta> obterPastasDoUsuarioLogado(@RequestParam("size") int size, @RequestParam("page") int page) {
        return this.pastaService.listarPastasDoUsuario(size, page);
    }

    @PostMapping("{nome}")
    @Transactional
    public Mono<Void> criarPasta(@PathVariable("nome") String nome) {
        return this.verificadorPastas
                .lancarExcecaoQuandoPastaDeUsuarioExistirNome(this.usuarioLogado.obterIdUsuarioLogado(), nome)
                .doOnNext(nomePasta -> this.pastaDiscoService.criarPastaNoDisco(nomePasta))
                .flatMap(nomePasta -> this.pastaService.criarPasta(Mono.just(nomePasta)))
                .then(Mono.empty());
    }

    @DeleteMapping("{nome}")
    @Transactional
    public Mono<Void> deletarPasta(@PathVariable("nome") String nome) {
        return this.pastaService.removerPasta(nome)
                .flatMap(nomePasta -> {
                    this.pastaDiscoService.removerPastaDoDisco(nomePasta);
                    return Mono.empty();
                });
    }

    @PatchMapping("{nome}")
    @Transactional
    public Mono<Void> atualizarNomePasta(@PathVariable("nome") String nome,
                                         @RequestParam("novoNome") String novoNome) {
        return this.pastaService.atualizarPasta(nome, novoNome)
                .flatMap(pasta -> {
                    this.pastaDiscoService.alterarNomePastaNoDisco(nome, novoNome);
                    return Mono.empty();
                });
    }

    @PatchMapping("mudar-favorito/{id}")
    @Transactional
    public Mono<Void> mudarEstadoFavorito(@PathVariable("id") String id) {
        return this.pastaService.mudarEstadoFavorito(Mono.just(id));
    }

}
