package com.efigueredo.file_storage.video_service.service;

import com.efigueredo.file_storage.video_service.service.dto.DtoUploadVideo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvertedorStringJsonParaDto {

    @Autowired
    private ObjectMapper mapper;

    public DtoUploadVideo converterStringParaDtoUpload(String json) {
        try {
            return this.mapper.readValue(json, DtoUploadVideo.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
