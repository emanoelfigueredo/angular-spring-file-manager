package com.efigueredo.file_storage.video_service.domain;

import com.efigueredo.file_storage.shared.domain.FileStorageArquivo;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("videos")
public class Video extends FileStorageArquivo {

    @Id
    private String id;

}
