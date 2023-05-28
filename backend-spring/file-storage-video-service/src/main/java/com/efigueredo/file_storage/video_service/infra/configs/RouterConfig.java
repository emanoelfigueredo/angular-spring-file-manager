package com.efigueredo.file_storage.video_service.infra.configs;

import com.efigueredo.file_storage.video_service.infra.controller.PastaController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

//    @Bean
//    public RouterFunction<ServerResponse> routes(PastaController controller) {
//        return route(POST("/videos/pastas/{nome}").and(accept(MediaType.APPLICATION_JSON)), controller::criarPasta)
//                .andRoute(GET("/videos/pastas").and(accept(MediaType.APPLICATION_JSON)), controller::obterPastasDoUsuarioLogado)
//                .andRoute(DELETE("/videos/pastas/{id}").and(accept(MediaType.APPLICATION_JSON)), controller::deletarPasta)
//                .andRoute(PATCH("/videos/pastas/{id}").and(accept(MediaType.APPLICATION_JSON)), controller::atualizarNomePasta)
//                .andRoute(PATCH("/videos/pastas/mudar-favorito/{id}").and(accept(MediaType.APPLICATION_JSON)), controller::mudarEstadoFavorito);
//    }

}
