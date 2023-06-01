package com.efigueredo.file_storage.shared.service.pastas;

import com.efigueredo.file_storage.shared.infra.exception.FileStorageException;
import reactor.core.publisher.Mono;

public class VerificadorPastasImpl extends VerificadorPastas {

    @Override
    public Mono<String> lancarExcecaoQuandoPastaDeUsuarioNaoExistirNome(long idUsuario, String nome) {
        return super.pastaRepository.existsByIdUsuarioAndNome(idUsuario, nome)
                .doOnNext(resultado -> {
                    if(!resultado) {
                        throw new FileStorageException("Pasta não existe", "A pasta de nome " + nome + " não existe" +
                                " no sistema.", "", 404);
                    }
                })
                .flatMap(a -> Mono.just(nome));
    }

    @Override
    public Mono<String> lancarExcecaoQuandoPastaDeUsuarioExistirNome(long idUsuario, String nome) {
        return super.pastaRepository.existsByIdUsuarioAndNome(idUsuario, nome)
                .doOnNext(resultado -> {
                    if(resultado) {
                        throw new FileStorageException("Pasta já existente", "A pasta de nome " + nome + " já existe para" +
                                " o usuário", "", 404);
                    }
                })
                .flatMap(a -> Mono.just(nome));
    }

    @Override
    public Mono<String> lancarExcecaoQuandoPastaDeUsuarioNaoExistirId(long idUsuarioLogado, String id) {
        return super.pastaRepository.existsByIdUsuarioAndId(idUsuarioLogado, id)
                .doOnNext(resultado -> {
                    if(!resultado) {
                        throw new FileStorageException("Pasta não existe", "A pasta de id " + id + " não existe" +
                                " no sistema.", "", 404);
                    }
                })
                .flatMap(a -> Mono.just(id));
    }

}
