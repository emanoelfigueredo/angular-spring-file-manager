package com.efigueredo.file_storage.shared.service;

import com.efigueredo.file_storage.shared.service.dto.FileStorageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvertedorStringJsonParaDto {

    @Autowired
    private ObjectMapper mapper;

    public FileStorageDto converterStringParaDtoUpload(String json) {
        try {
            return this.mapper.readValue(json, FileStorageDto.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
