package com.efigueredo.file_storage.shared.infra.exception;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class TratadorExcecoes {

    @Autowired
    private ModelMapper modelMapper;

    @ExceptionHandler(FileStorageException.class)
    public Mono<ResponseEntity<RespostaErro>> tratarFileStorageException(FileStorageException e) {
        RespostaErro erro = this.modelMapper.map(e, RespostaErro.class);
        ResponseEntity<RespostaErro> responseEntity = ResponseEntity.status(erro.getStatus()).body(erro);
        return Mono.just(responseEntity);
    }

}
