package com.efigueredo.file_storage.shared.domain;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PastaRepository extends ReactiveMongoRepository<Pasta, String> {

    Flux<Pasta> findAllByIdUsuario(long idUsuario, Sort sort);

    Mono<Pasta> findByIdUsuarioAndId(long idUsuario, String id);

    Mono<Boolean> existsByIdUsuarioAndNome(long idUsuario, String nome);

    Mono<Pasta> findByIdUsuarioAndNome(long idUsuario, String nome);

    Mono<Boolean> existsByIdUsuarioAndId(long idUsuario, String id);

}
