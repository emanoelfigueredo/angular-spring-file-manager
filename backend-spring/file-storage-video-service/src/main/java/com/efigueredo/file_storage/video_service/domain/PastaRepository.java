package com.efigueredo.file_storage.video_service.domain;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PastaRepository extends ReactiveMongoRepository<Pasta, String> {

}
