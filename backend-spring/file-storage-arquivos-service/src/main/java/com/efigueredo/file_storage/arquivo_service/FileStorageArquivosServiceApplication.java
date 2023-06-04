package com.efigueredo.file_storage.arquivo_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.efigueredo.file_storage.*"})
@EnableReactiveMongoRepositories(basePackages = {"com.efigueredo.file_storage.*"})
public class FileStorageArquivosServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileStorageArquivosServiceApplication.class, args);
	}

}
