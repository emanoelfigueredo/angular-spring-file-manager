package com.efigueredo.file_storage.shared.service.files;

import com.efigueredo.file_storage.shared.domain.Arquivo;
import org.springframework.core.io.Resource;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

public interface FileStorageDiscoService {

    Mono<Tuple2<FilePart, Mono<Arquivo>>> salvarFileNoDisco(Tuple2<FilePart, Mono<Arquivo>> tuple);
    Mono<Arquivo> removerFileDoDisco(Arquivo fileStorageArquivo);
    Mono<Resource> obterFileDoDisco(Arquivo fileStorageArquivo);

}
