package com.efigueredo.file_storage.shared.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document("arquivos")
public class Arquivo {

    @Id
    private String id;
    private String idPasta;
    private long idUsuario;
    private String nome;
    private String extencao;
    private LocalDateTime momentoUpload;
    private boolean favorito;
    private long tamanho;

    public void inverterFavorito() {
        this.favorito = !this.favorito;
    }

}
