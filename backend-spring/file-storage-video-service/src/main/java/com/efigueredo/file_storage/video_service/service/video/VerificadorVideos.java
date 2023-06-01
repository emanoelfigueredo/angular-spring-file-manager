package com.efigueredo.file_storage.video_service.service.video;

import com.efigueredo.file_storage.video_service.domain.VideoRepository;
import com.efigueredo.file_storage.shared.infra.exception.FileStorageException;
import com.efigueredo.file_storage.shared.service.files.VerificadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class VerificadorVideos implements VerificadorService {

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public Mono<String> lancarExcecaoQuandoFileDeIdNaoExistir(String idFile) {
        return this.videoRepository.existsById(idFile)
                .flatMap(existe -> {
                    if(!existe) {
                        throw new FileStorageException("Vídeo não encontrado", "Vídeo de id " + idFile + " não existe" +
                                " no sistema.", "", 404);
                    }
                    return Mono.just(idFile);
                });
    }
}
