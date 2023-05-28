package com.efigueredo.file_storage.video_service.infra.excetion;

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

}
