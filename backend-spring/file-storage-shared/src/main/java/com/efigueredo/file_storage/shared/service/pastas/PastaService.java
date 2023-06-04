package com.efigueredo.file_storage.shared.service.pastas;

import com.efigueredo.file_storage.shared.domain.Arquivo;
import com.efigueredo.file_storage.shared.domain.Pasta;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PastaService {

    Mono<Pasta> criarPasta(String nomePasta);
    Mono<String> removerPasta(String nomePasta);
    Mono<Pasta> atualizarPasta(String nomePasta, String novoNome);
    Flux<Pasta> listarPastasDoUsuario(int size, int page);
    Mono<Pasta> obterPastaDoUsuario(Mono<String>  idPasta);
    Mono<Void> mudarEstadoFavorito(Mono<String>  idPasta);
    Mono<Arquivo> aumentarTamanhoDaPasta(Arquivo file);
    Mono<Arquivo> diminuirTamanhoDaPasta(Arquivo file);
    Mono<Arquivo> contabilizarAdicaoArquivoNaPasta(Arquivo file);
    Mono<Arquivo> contabilizarRemocaoArquivoNaPasta(Arquivo file);
    Mono<Pasta> removerTodosArquivosDaPasta(String idPasta);

}
