package com.efigueredo.file_storage.video_service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document("pastas")
public class Pasta {

    @Id
    private String id;
    private long idUsuario;
    private String nome;
    private boolean favorito;
    private int quatidadeArquivos;
    private long tamanho;

    public void trocarFavorito() {
        this.favorito = !this.favorito;
    }

}
