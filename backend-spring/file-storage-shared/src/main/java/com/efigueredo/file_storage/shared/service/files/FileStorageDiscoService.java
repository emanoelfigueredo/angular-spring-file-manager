package com.efigueredo.file_storage.shared.service.files;

import com.efigueredo.file_storage.shared.domain.FileStorageArquivo;
import org.springframework.core.io.Resource;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

public interface FileStorageDiscoService {

    Mono<Tuple2<FilePart, Mono<FileStorageArquivo>>> salvarFileNoDisco(Tuple2<FilePart, Mono<FileStorageArquivo>> tuple);
    Mono<FileStorageArquivo> removerFileDoDisco(FileStorageArquivo fileStorageArquivo);
    Mono<Resource> obterFileDoDisco(FileStorageArquivo fileStorageArquivo);

}
