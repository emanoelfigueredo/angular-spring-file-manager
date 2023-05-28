package com.efigueredo.file_storage.video_service.service.pastas;

import com.efigueredo.file_storage.video_service.infra.excetion.FileStorageException;
import reactor.core.publisher.Mono;

public class VerificadorPastasImpl extends VerificadorPastas {

    @Override
    public Mono<String> lancarExcecaoQuandoPastaDeUsuarioNaoExistirId(long idUsuario, String idPasta) {
        return super.pastaRepository.existsByIdUsuarioAndId(idUsuario, idPasta)
                .doOnNext(resultado -> {
                    if(!resultado) {
                        throw new FileStorageException("Pasta de id não existe", "A pasta de id " + idPasta + " não existe" +
                                " no sistema.", "", 404);
                    }
                })
                .flatMap(a -> Mono.just(idPasta));
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

}
