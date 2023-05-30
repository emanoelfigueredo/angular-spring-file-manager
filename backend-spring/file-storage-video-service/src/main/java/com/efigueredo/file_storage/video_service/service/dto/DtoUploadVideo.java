package com.efigueredo.file_storage.video_service.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DtoUploadVideo {

    private String nome;
    private String extencao;
    private long tamanho;
    private String idPasta;

}
