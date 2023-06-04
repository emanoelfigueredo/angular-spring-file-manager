package com.efigueredo.file_storage.shared.service.files;

import com.efigueredo.file_storage.shared.domain.Arquivo;
import com.efigueredo.file_storage.shared.service.dto.FileStorageDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ArquivoService {

    Flux<Arquivo> listarVideosDoUsuario(String idPasta);
    Mono<Arquivo> uploadFile(FileStorageDto dto);
    Mono<Arquivo> alterarNomeFileDoUsuario(String idFile, String novoNome);
    Mono<Arquivo> removerFileDoUsuario(Arquivo file);
    Mono<String> removerTodosFilesDaPasta(String nomePasta);
    Mono<Arquivo> obterFileDoUsuario(String idFile);
    Mono<Void> inverterValorFavoritoFile(String idFile);

}
