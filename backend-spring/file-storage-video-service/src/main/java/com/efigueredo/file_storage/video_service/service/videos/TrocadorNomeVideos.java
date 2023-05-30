package com.efigueredo.file_storage.video_service.service.videos;

import com.efigueredo.file_storage.video_service.service.dto.DtoUploadVideo;
import reactor.core.publisher.Mono;

public interface TrocadorNomeVideos {

    public Mono<DtoUploadVideo> trocarNomeCasoJaExista(DtoUploadVideo upload);

}
