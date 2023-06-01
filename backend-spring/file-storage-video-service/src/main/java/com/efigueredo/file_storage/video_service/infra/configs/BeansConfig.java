package com.efigueredo.file_storage.video_service.infra.configs;

import com.efigueredo.file_storage.video_service.service.video.*;
import com.efigueredo.file_storage.shared.service.files.FileStorageDiscoService;
import com.efigueredo.file_storage.shared.service.files.TrocadorNomeService;
import com.efigueredo.file_storage.shared.service.files.VerificadorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public VideoService videoService() {
        return new VideoServiceImpl();
    }

    @Bean
    public VerificadorService verificadorVideos() {
        return new VerificadorVideos();
    }

    @Bean
    public TrocadorNomeService trocadorNomeVideos() {
        return new TrocadorNomeVideos();
    }

    @Bean
    public FileStorageDiscoService videosDiscoService() {
        return new VideosDiscoService();
    }


}
