package com.efigueredo.file_storage.shared.service;

import com.efigueredo.file_storage.shared.infra.exception.FileStorageException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServicesUtils<T> {

    public Mono<T> executarQuandoPastaExistirMono(boolean resultado, String idPasta,
                                                   ExecutorFuncaoQuandoPastaExistirMono<T> executor) {
        if(resultado) {
            System.out.println("Pasta existe, executar");
            return executor.executar();
        }
        System.out.println("Pasta nao existe, excecao");
        lancarExcecaoPastaIdInexistente(idPasta);
        return null;
    }

    public Mono<T> executarQuandoPastaExistirMonoNome(boolean resultado, String nome,
                                                  ExecutorFuncaoQuandoPastaExistirMono<T> executor) {
        if(resultado) {
            return executor.executar();
        }
        lancarExcecaoPastaNomeInexistente(nome);
        return null;
    }

    public Flux<T> executarQuandoPastaExistirFlux(boolean resultado, String idPasta,
                                                   ExecutorFuncaoQuandoPastaExistirFlux<T> executor) {
        if(resultado) {
            return executor.executar();
        }
        lancarExcecaoPastaIdInexistente(idPasta);
        return null;
    }

    public static void lancarExcecaoPastaIdInexistente(String idPasta) {
        throw new FileStorageException("Pasta não encontrada", "A pasta de id "
                + idPasta + " não existe.", "", 404);
    }

    public static void lancarExcecaoPastaNomeInexistente(String nomePasta) {
        throw new FileStorageException("Pasta não encontrada", "A pasta de nome "
                + nomePasta + " não existe.", "", 404);
    }

}
