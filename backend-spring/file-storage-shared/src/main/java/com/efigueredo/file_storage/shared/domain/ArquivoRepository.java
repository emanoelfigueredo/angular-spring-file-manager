package com.efigueredo.file_storage.shared.domain;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ArquivoRepository extends ReactiveMongoRepository<Arquivo, String> {

    Flux<Arquivo> findAllByIdUsuarioAndIdPastaOrderByMomentoUploadDesc(long idUsuario, String idPasta);

    Mono<Boolean> existsByNomeAndIdPasta(String nome, String idPasta);

    Mono<Arquivo> findByIdAndIdUsuario(String idVideo, long idUsuario);

    Mono<Long> countByIdPastaAndNomeContaining(String idPasta, String nome);

    Mono<?> deleteAllByIdUsuarioAndIdPasta(long idUsuarioLogado, String id);
}
