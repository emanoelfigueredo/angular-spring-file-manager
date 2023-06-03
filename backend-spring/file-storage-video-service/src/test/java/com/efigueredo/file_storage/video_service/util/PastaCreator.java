package com.efigueredo.file_storage.video_service.util;

import com.efigueredo.file_storage.shared.domain.Pasta;
import reactor.core.publisher.Mono;

public class PastaCreator {

    public static Pasta pastaParaSerCriada() {
        return Pasta.builder()
                .id(null)
                .nome("Pasta1")
                .idUsuario(1L)
                .favorito(false)
                .quatidadeArquivos(0)
                .tamanho(0)
                .build();
    }

    public static String nomePastaCriar() {
        return "Pasta1";
    }

    public static Pasta pastaParaSerValidada() {
        return Pasta.builder()
                .id("IdPasta1")
                .nome("Pasta1")
                .idUsuario(1L)
                .favorito(false)
                .quatidadeArquivos(0)
                .tamanho(0)
                .build();
    }

    public static Pasta pastaParaSerAtualizada() {
        return Pasta.builder()
                .id("IdPasta1")
                .nome("Pasta2")
                .idUsuario(1L)
                .favorito(false)
                .quatidadeArquivos(0)
                .tamanho(0)
                .build();
    }

}
