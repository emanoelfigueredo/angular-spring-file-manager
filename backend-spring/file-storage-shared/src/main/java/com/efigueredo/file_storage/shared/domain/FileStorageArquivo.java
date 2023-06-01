package com.efigueredo.file_storage.shared.domain;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FileStorageArquivo {

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
