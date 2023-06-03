package com.efigueredo.file_storage.shared.infra.controller;

import com.efigueredo.file_storage.shared.service.dto.DeleteListFilesDto;
import org.springframework.core.io.Resource;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FileController<T> {

    Flux<T> listarVideosDoUsuario(@PathVariable String idPasta);
    Mono<Resource> obterVideo(@PathVariable String id);
    Mono<Void> alterarNomeVideo(@PathVariable String id, @RequestParam("novoNome") String novoNome);
    Mono<Void> removerVideo(@PathVariable String id);
    Mono<Void> removerTodosVideosDaPasta(@PathVariable String idPasta);
    Flux<Void> removerMuitosVideos(@RequestBody DeleteListFilesDto ids);
    Mono<Void> inverterFavorito(@PathVariable String id);
    Mono<?> uploadVideo(@RequestPart("videos") Flux<FilePart> videos, @RequestPart("dados") Flux<String> dadosVideos);

}
