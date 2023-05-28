package com.efigueredo.file_storage.video_service.infra.controller;

import com.efigueredo.file_storage.video_service.domain.Pasta;
import com.efigueredo.file_storage.video_service.infra.excetion.FileStorageException;
import com.efigueredo.file_storage.video_service.service.pastas.PastaDiscoService;
import com.efigueredo.file_storage.video_service.service.pastas.PastaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequestMapping("videos/pastas")
public class PastaController {

    @Autowired
    private PastaService pastaService;

    @Autowired
    private PastaDiscoService pastaDiscoService;

    @GetMapping
    public Flux<Pasta> obterPastasDoUsuarioLogado(@RequestParam("size") int size, @RequestParam("page") int page) {
        return this.pastaService.listarPastasDoUsuario(size, page);
    }

    @PostMapping("{nome}")
    public Mono<Void> criarPasta(@PathVariable("nome") String nome) {
        return this.pastaService.criarPasta(Mono.just(nome))
                .doOnNext(this.pastaDiscoService::criarPastaNoDisco)
                .then(Mono.empty());
    }

    @DeleteMapping("{id}")
    public Mono<Void> deletarPasta(@PathVariable("id") String id) {
        return this.pastaService.removerPasta(Mono.just(id))
                .doOnNext(this.pastaDiscoService::removerPastaDoDisco)
                .then(Mono.empty());
    }

    @PatchMapping("{id}")
    public Mono<Void> atualizarNomePasta(@PathVariable("id") String id,
                                         @RequestParam("novoNome") String novoNome) {
        return this.pastaService.atualizarPasta(Mono.just(id), novoNome)
                .then(Mono.empty());
    }

    @PatchMapping("mudar-favorito/{id}")
    public Mono<Void> mudarEstadoFavorito(@PathVariable("id") String id) {
        return this.pastaService.mudarEstadoFavorito(Mono.just(id));
    }

}
