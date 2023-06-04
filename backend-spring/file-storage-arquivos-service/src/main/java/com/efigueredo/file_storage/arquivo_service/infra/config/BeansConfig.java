package com.efigueredo.file_storage.arquivo_service.infra.config;

import com.efigueredo.file_storage.arquivo_service.service.ResolvedorPathPastaArquivos;
import com.efigueredo.file_storage.shared.service.pastas.ResolvedorPathPasta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public ResolvedorPathPasta resolvedorPathPasta() {
        return new ResolvedorPathPastaArquivos();
    }

}
