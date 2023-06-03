package com.efigueredo.file_storage.audios_service.infra.config;

import com.efigueredo.file_storage.audios_service.service.audios.ResolvedorPathPastasAudios;
import com.efigueredo.file_storage.shared.service.files.FileStorageDiscoService;
import com.efigueredo.file_storage.shared.service.files.TrocadorNomeService;
import com.efigueredo.file_storage.shared.service.files.VerificadorService;
import com.efigueredo.file_storage.shared.service.pastas.ResolvedorPathPasta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public ResolvedorPathPasta resolvedorPathPasta() {
        return new ResolvedorPathPastasAudios();
    }


}
