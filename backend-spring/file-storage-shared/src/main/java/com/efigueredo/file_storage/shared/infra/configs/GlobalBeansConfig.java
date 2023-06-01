package com.efigueredo.file_storage.shared.infra.configs;

import com.efigueredo.file_storage.shared.service.pastas.*;
import com.efigueredo.file_storage.shared.service.usuarios.UsuarioLogado;
import com.efigueredo.file_storage.shared.service.usuarios.UsuarioLogadoImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalBeansConfig {

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
    public WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }

}
