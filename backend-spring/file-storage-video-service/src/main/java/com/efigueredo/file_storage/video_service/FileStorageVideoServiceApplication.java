package com.efigueredo.file_storage.video_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
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
public class  FileStorageVideoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileStorageVideoServiceApplication.class, args);
	}

}
