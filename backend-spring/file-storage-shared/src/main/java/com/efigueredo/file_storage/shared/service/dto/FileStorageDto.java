package com.efigueredo.file_storage.shared.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FileStorageDto {

    private String nome;
    private String extencao;
    private long tamanho;
    private String idPasta;

}
