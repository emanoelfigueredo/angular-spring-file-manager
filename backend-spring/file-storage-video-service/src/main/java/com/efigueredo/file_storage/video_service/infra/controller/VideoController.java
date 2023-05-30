package com.efigueredo.file_storage.video_service.infra.controller;

import com.efigueredo.file_storage.video_service.domain.Video;
import com.efigueredo.file_storage.video_service.infra.excetion.RespostaErro;
import com.efigueredo.file_storage.video_service.service.ConvertedorStringJsonParaDto;
import com.efigueredo.file_storage.video_service.service.dto.DtoUploadVideo;
import com.efigueredo.file_storage.video_service.service.dto.DtoUploadVideos;
import com.efigueredo.file_storage.video_service.service.videos.VerificadorVideos;
import com.efigueredo.file_storage.video_service.service.videos.VideoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("videos")
@CrossOrigin("http://localhost:4200")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private VerificadorVideos verificadorVideos;

    @Autowired
    private ConvertedorStringJsonParaDto convertedorStringJsonParaDto;

    @GetMapping
    public Flux<Video> listarVideosDoUsuario() {
        return this.videoService.listarVideosDoUsuario();
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> uploadVideo(@RequestPart("videos") Flux<FilePart> videos,
                                            @RequestPart("dados") Flux<String> dadosVideos) {
        return Flux.zip(videos, dadosVideos)
                .map(upload -> upload.mapT2(this.convertedorStringJsonParaDto::converterStringParaDtoUpload))
                .flatMap(upload -> this.videoService.uploadVideos(upload.getT2()))
                .then();
    }

    @PutMapping("{id}")
    public Mono<Void> alterarNomeVideo(@PathVariable String id,
                                       @RequestParam("novoNome") String novoNome) {
        return this.videoService.alterarNomeVideoDoUsuario(id, novoNome);
    }

    @DeleteMapping("{id}")
    public Mono<Void> removerVideo(@PathVariable String id) {
        return this.videoService.removerVideoDoUsuario(id);
    }

}
