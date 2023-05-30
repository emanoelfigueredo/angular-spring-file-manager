package com.efigueredo.file_storage.video_service.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class DtoUploadVideos {

    List<DtoUploadVideo> listaVideosDto;

}
