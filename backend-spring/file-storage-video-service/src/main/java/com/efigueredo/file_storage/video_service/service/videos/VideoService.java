package com.efigueredo.file_storage.video_service.service.videos;

import com.efigueredo.file_storage.video_service.domain.Video;
import com.efigueredo.file_storage.video_service.service.dto.DtoUploadVideo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VideoService {

    Flux<Video> listarVideosDoUsuario();
    Flux<Video> uploadVideos(DtoUploadVideo dtoUploadVideosMono);
    Mono<Void> alterarNomeVideoDoUsuario(String idVideo, String novoNome);
    Mono<Void> removerVideoDoUsuario(String idVideo);

}
