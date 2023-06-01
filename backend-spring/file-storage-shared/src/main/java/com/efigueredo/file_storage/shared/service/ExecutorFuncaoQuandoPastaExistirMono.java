package com.efigueredo.file_storage.shared.service;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface ExecutorFuncaoQuandoPastaExistirMono<T> {

    Mono<T> executar();

}
