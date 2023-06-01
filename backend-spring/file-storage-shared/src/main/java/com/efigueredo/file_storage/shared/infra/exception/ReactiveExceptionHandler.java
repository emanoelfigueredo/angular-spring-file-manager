//package com.efigueredo.file_storage.video_service.infra.excetion;
//
//import lombok.extern.slf4j.Slf4j;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.web.WebProperties;
//import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
//import org.springframework.boot.web.reactive.error.ErrorAttributes;
//import org.springframework.context.ApplicationContext;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.codec.ServerCodecConfigurer;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.server.*;
//import reactor.core.publisher.Mono;
//
//import java.util.Map;
//
//@Slf4j
//@Component
//@Order(-2)
//public class ReactiveExceptionHandler extends AbstractErrorWebExceptionHandler {
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//
//    public ReactiveExceptionHandler(ErrorAttributes errorAttributes, WebProperties.Resources resources,
//                                    ApplicationContext applicationContext,
//                                    ServerCodecConfigurer configurer) {
//        super(errorAttributes, resources, applicationContext);
//        this.setMessageWriters(configurer.getWriters());
//    }
//
//    @Override
//    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
//        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
//    }
//
//    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
//
//        Throwable error = getError(request);
//        log.info("An error has been occurred", error);
//        HttpStatus httpStatus;
//        RespostaErro respostaErro;
//        if (error instanceof FileStorageException e) {
//            respostaErro = this.modelMapper.map(e, RespostaErro.class);
//        } else {
//            respostaErro = new RespostaErro("TESTE", "TESTE", "", 500);
//        }
//        return ServerResponse
//                .status(respostaErro.getStatus())
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(BodyInserters.fromValue(respostaErro));
//    }
//}