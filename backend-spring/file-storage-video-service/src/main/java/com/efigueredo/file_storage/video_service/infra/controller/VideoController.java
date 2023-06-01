package com.efigueredo.file_storage.video_service.infra.controller;

import com.efigueredo.file_storage.shared.domain.PastaRepository;
import com.efigueredo.file_storage.shared.service.dto.DeleteListFilesDto;
import com.efigueredo.file_storage.shared.service.pastas.PastaDiscoService;
import com.efigueredo.file_storage.shared.service.pastas.PastaService;
import com.efigueredo.file_storage.video_service.domain.Video;
import com.efigueredo.file_storage.shared.service.ConvertedorStringJsonParaDto;
import com.efigueredo.file_storage.video_service.service.video.VideoService;
import com.efigueredo.file_storage.shared.service.files.FileStorageDiscoService;
import com.efigueredo.file_storage.shared.service.files.VerificadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("videos")
@CrossOrigin("http://localhost:4200")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private VerificadorService verificadorVideos;

    @Autowired
    private ConvertedorStringJsonParaDto convertedorStringJsonParaDto;

    @Autowired
    private PastaRepository pastaRepository;

    @Autowired
    private FileStorageDiscoService videosDiscoService;

    @Autowired
    private PastaDiscoService pastaDiscoService;

    @GetMapping("pasta/{idPasta}")
    public Flux<Video> listarVideosDoUsuario(@PathVariable String idPasta) {
        return this.videoService.listarVideosDoUsuario(idPasta);
    }

    @GetMapping("{id}")
    public Mono<Resource> obterVideo(@PathVariable String id) {
        return this.videoService.obterFileDoUsuario(id)
                .flatMap(this.videosDiscoService::obterFileDoDisco);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<?> uploadVideo(@RequestPart("videos") Flux<FilePart> videos,
                                            @RequestPart("dados") Flux<String> dadosVideos) {
        return Flux.zip(videos, dadosVideos)
                .map(upload -> upload.mapT2(this.convertedorStringJsonParaDto::converterStringParaDtoUpload))
                .map(upload -> upload.mapT2(this.videoService::uploadFile))
                .flatMap(this.videosDiscoService::salvarFileNoDisco)
                .then(Mono.empty());
    }

    @PatchMapping("{id}")
    public Mono<Void> alterarNomeVideo(@PathVariable String id,
                                       @RequestParam("novoNome") String novoNome) {
        return this.videoService.alterarNomeFileDoUsuario(id, novoNome)
                .then(Mono.empty());
    }

    @DeleteMapping("{id}")
    public Mono<Void> removerVideo(@PathVariable String id) {
        return this.videoService.obterFileDoUsuario(id)
                .flatMap(this.videosDiscoService::removerFileDoDisco)
                .flatMap(video -> this.videoService.removerFileDoUsuario(video))
                .then(Mono.empty());
    }

    @DeleteMapping("all/{idPasta}")
    public Mono<Void> removerTodosVideosDaPasta(@PathVariable String idPasta) {
        return this.videoService.removerTodosFilesDaPasta(idPasta)
                .flatMap(id -> this.pastaDiscoService.removerTodosFilesDaPasta(idPasta));

    }

    @DeleteMapping("list")
    public Flux<Void> removerMuitosVideos(@RequestBody DeleteListFilesDto ids) {
        return Flux.fromStream(ids.getListaIdFiles().stream())
                .flatMap(this::removerVideo);

    }

    @PatchMapping("inverter-favorito/{id}")
    public Mono<Void> inverterFavorito(@PathVariable String id) {
        return this.videoService.inverterValorFavoritoFile(id);
    }

}
