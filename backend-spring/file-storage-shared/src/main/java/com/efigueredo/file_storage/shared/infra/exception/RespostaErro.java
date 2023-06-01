package com.efigueredo.file_storage.shared.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RespostaErro {

    private String title;
    private String type;
    private String details;
    private int status;

}
