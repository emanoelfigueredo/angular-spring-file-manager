package com.efigueredo.file_storage.video_service.infra.configs;

import com.efigueredo.file_storage.shared.service.pastas.ResolvedorPathPasta;
import com.efigueredo.file_storage.video_service.service.ResolvedorPathVideos;
import com.efigueredo.file_storage.video_service.service.video.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public ResolvedorPathPasta resolvedorPathPasta() {
        return new ResolvedorPathVideos();
    }


}
