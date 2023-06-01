package com.efigueredo.filestorage.audios_service.domain;

import com.efigueredo.file_storage.shared.domain.FileStorageArquivo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("videos")

public class Audio extends FileStorageArquivo {

    @Id
    private String id;

}
