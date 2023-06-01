package com.efigueredo.file_storage.shared.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class FileStorageException extends RuntimeException {

    private String title;
    private String type;
    private String details;
    private int status;
    private String idPasta;

    public FileStorageException(String title, String type, String details, int status) {
        this.title = title;
        this.type = type;
        this.details = details;
        this.status = status;
    }

}
