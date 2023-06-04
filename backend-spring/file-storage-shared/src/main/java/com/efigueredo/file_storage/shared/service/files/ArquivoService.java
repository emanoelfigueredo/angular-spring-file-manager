package com.efigueredo.file_storage.shared.service.files;

import com.efigueredo.file_storage.shared.domain.Arquivo;
import com.efigueredo.file_storage.shared.service.dto.FileStorageDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ArquivoService {

    Flux<Arquivo> listarArquivosDoUsuario(String idPasta);
    Mono<Arquivo> uploadArquivo(FileStorageDto dto);
    Mono<Arquivo> alterarNomeArquivoDoUsuario(String idFile, String novoNome);
    Mono<Arquivo> removerArquivoDoUsuario(Arquivo file);
    Mono<String> removerTodosOsArquivosDaPasta(String nomePasta);
    Mono<Arquivo> obterArquivoDoUsuario(String idFile);
    Mono<Void> inverterValorFavoritoFile(String idFile);

}
