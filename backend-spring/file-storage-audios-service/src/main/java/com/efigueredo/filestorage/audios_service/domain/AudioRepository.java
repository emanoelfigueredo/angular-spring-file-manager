package com.efigueredo.filestorage.audios_service.domain;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AudioRepository extends ReactiveMongoRepository<Audio, String> {

}
