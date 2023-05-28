package com.efigueredo.file_storage.video_service.infra.excetion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RespostaErro {

    private String title;
    private String type;
    private String details;
    private int status;

}
