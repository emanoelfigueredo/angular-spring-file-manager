package com.efigueredo.file_storage.video_service.service.videos;

import com.efigueredo.file_storage.video_service.domain.VideoRepository;
import com.efigueredo.file_storage.video_service.infra.excetion.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class VerificadorVideosImpl implements VerificadorVideos {

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public Mono<String> lancarExcecaoQuandoVideoDeIdNaoExistir(String id) {
        return this.videoRepository.existsById(id)
                .flatMap(existe -> {
                    if(!existe) {
                        throw new FileStorageException("Vídeo não encontrado", "Vídeo de id " + id + " não existe" +
                                " no sistema.", "", 404);
                    }
                    return Mono.just(id);
                });
    }

}
