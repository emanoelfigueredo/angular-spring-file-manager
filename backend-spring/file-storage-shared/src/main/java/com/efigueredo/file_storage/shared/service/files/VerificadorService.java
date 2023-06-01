package com.efigueredo.file_storage.shared.service.files;

import reactor.core.publisher.Mono;

public interface VerificadorService {

    Mono<String> lancarExcecaoQuandoFileDeIdNaoExistir(String idFile);

}
