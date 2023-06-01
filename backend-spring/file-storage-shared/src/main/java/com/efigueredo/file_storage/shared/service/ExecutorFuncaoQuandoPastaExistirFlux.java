package com.efigueredo.file_storage.shared.service;

import reactor.core.publisher.Flux;

@FunctionalInterface
public interface ExecutorFuncaoQuandoPastaExistirFlux<T> {

    Flux<T> executar();

}
