package com.efigueredo.file_storage.video_service.infra.configs;

import com.efigueredo.file_storage.video_service.service.pastas.*;
import com.efigueredo.file_storage.video_service.service.usuarios.UsuarioLogado;
import com.efigueredo.file_storage.video_service.service.usuarios.UsuarioLogadoImpl;
import com.efigueredo.file_storage.video_service.service.videos.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PastaService pastaService() {
        return new PastaServiceImpl();
    }

    @Bean
    public VerificadorPastas verificadorPastas() {
        return new VerificadorPastasImpl();
    }

    @Bean
    public UsuarioLogado usuarioLogado() {
        return new UsuarioLogadoImpl();
    }

    @Bean
    public PastaDiscoService pastaDiscoService() {
        return new PastaDiscoServiceImpl();
    }

    @Bean
    public ResolvedorPathPasta resolvedorPathPasta() {
        return new ResolvedorPathPastasImpl();
    }

    @Bean
    public VideoService videoService() {
        return new VideoServiceImpl();
    }

    @Bean
    public VerificadorVideos verificadorVideos() {
        return new VerificadorVideosImpl();
    }

    @Bean
    public TrocadorNomeVideos trocadorNomeVideos() {
        return new TrocadorNomeVideosImpl();
    }


}
