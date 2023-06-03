package com.efigueredo.file_storage.video_service.domain;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface VideoRepository extends ReactiveMongoRepository<Video, String> {

    Flux<Video> findAllByIdUsuarioAndIdPastaOrderByMomentoUploadDesc(long idUsuario, String idPasta);

    Mono<Boolean> existsByNomeAndIdPasta(String nome, String idPasta);

    Mono<Video> findByIdAndIdUsuario(String idVideo, long idUsuario);

    Mono<Long> countByIdPastaAndNomeContaining(String idPasta, String nome);

    Mono<?> deleteAllByIdUsuarioAndIdPasta(long idUsuarioLogado, String id);
}
