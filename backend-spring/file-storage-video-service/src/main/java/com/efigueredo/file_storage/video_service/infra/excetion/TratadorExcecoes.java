package com.efigueredo.file_storage.video_service.infra.excetion;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class TratadorExcecoes {

    @Autowired
    private ModelMapper modelMapper;

    @ExceptionHandler(FileStorageException.class)
    public Mono<RespostaErro> tratarFileStorageException(FileStorageException e) {
        return Mono.just(this.modelMapper.map(e, RespostaErro.class));
    }

}
