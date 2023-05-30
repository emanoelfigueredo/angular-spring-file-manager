package com.efigueredo.file_storage.video_service.service.videos;

import reactor.core.publisher.Mono;

public interface VerificadorVideos {

    Mono<String> lancarExcecaoQuandoVideoDeIdNaoExistir(String nome);

}
