package com.efigueredo.file_storage.shared.service.files;

import com.efigueredo.file_storage.shared.service.dto.FileStorageDto;
import reactor.core.publisher.Mono;

public interface TrocadorNomeService {

    Mono<FileStorageDto> trocarNomeCasoJaExista(FileStorageDto upload);

}
