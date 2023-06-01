package com.efigueredo.file_storage.shared.service.pastas;

import com.efigueredo.file_storage.shared.domain.FileStorageArquivo;
import com.efigueredo.file_storage.shared.domain.Pasta;
import com.efigueredo.file_storage.shared.domain.PastaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class PastaService {

    @Autowired
    protected PastaRepository pastaRepository;

    @Autowired
    protected VerificadorPastas verificadorPastas;

    public abstract Mono<Pasta> criarPasta(Mono<String> nomePasta);
    public abstract Mono<String> removerPasta(String nomePasta);
    public abstract Mono<Pasta> atualizarPasta(String nomePasta, String novoNome);
    public abstract Flux<Pasta> listarPastasDoUsuario(int size, int page);
    public abstract Mono<Pasta> obterPastaDoUsuario(Mono<String>  idPasta);
    public abstract Mono<Void> mudarEstadoFavorito(Mono<String>  idPasta);
    public abstract Mono<FileStorageArquivo> aumentarTamanhoDaPasta(FileStorageArquivo file);
    public abstract Mono<FileStorageArquivo> diminuirTamanhoDaPasta(FileStorageArquivo file);
    public abstract Mono<FileStorageArquivo> contabilizarAdicaoArquivoNaPasta(FileStorageArquivo file);
    public abstract Mono<FileStorageArquivo> contabilizarRemocaoArquivoNaPasta(FileStorageArquivo file);
    public abstract Mono<Pasta> removerTodosArquivosDaPasta(String idPasta);

}
