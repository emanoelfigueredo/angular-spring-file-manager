package com.efigueredo.file_storage.shared.service.pastas;

import com.efigueredo.file_storage.shared.domain.PastaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

public abstract class VerificadorPastas {

    @Autowired
    protected PastaRepository pastaRepository;

    public abstract Mono<String> lancarExcecaoQuandoPastaDeUsuarioNaoExistirNome(long idUsuario, String nome);
    public abstract Mono<String> lancarExcecaoQuandoPastaDeUsuarioExistirNome(long idUsuario, String nome);
    public abstract Mono<String> lancarExcecaoQuandoPastaDeUsuarioNaoExistirId(long idUsuarioLogado, String id);
}
