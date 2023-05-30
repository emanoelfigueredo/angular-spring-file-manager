package com.efigueredo.file_storage.video_service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document("videos")
public class Video {

    @Id
    private String id;
    private long idUsuario;
    private String idPasta;
    private String nome;
    private String extencao;
    private LocalDateTime momentoUpload;
    private boolean favorito;
    private long tamanho;

}
