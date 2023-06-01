package com.efigueredo.file_storage.video_service.service.video;

import com.efigueredo.file_storage.video_service.domain.Video;
import com.efigueredo.file_storage.shared.service.files.FileStorageService;
import reactor.core.publisher.Flux;

public interface VideoService extends FileStorageService {

    Flux<Video> listarVideosDoUsuario(String idPasta);

}
