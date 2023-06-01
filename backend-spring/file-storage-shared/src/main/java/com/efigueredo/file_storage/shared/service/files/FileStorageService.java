package com.efigueredo.file_storage.shared.service.files;

import com.efigueredo.file_storage.shared.domain.FileStorageArquivo;
import com.efigueredo.file_storage.shared.service.dto.FileStorageDto;
import reactor.core.publisher.Mono;

public interface FileStorageService {

    Mono<FileStorageArquivo> uploadFile(FileStorageDto dto);
    Mono<FileStorageArquivo> alterarNomeFileDoUsuario(String idFile, String novoNome);
    Mono<FileStorageArquivo> removerFileDoUsuario(FileStorageArquivo file);
    Mono<String> removerTodosFilesDaPasta(String idPasta);
    Mono<FileStorageArquivo> obterFileDoUsuario(String idFile);
    Mono<Void> inverterValorFavoritoFile(String idFile);

}
