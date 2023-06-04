package com.efigueredo.file_storage.audios_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.efigueredo.file_storage.*",
		"com.efigueredo.file_storage.shared.domain.PastaRepository"
})
@EnableReactiveMongoRepositories(basePackages = {
		"com.efigueredo.file_storage.*",
		"com.efigueredo.file_storage.shared.domain.PastaRepository"
})
public class FileStorageAudiosServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileStorageAudiosServiceApplication.class, args);
	}

}
