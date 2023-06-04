package com.efigueredo.file_storage.imagens_service.service;

import com.efigueredo.file_storage.shared.service.pastas.ResolvedorPathPasta;
import org.springframework.stereotype.Service;

@Service
public class ResolvedorPathImagens extends ResolvedorPathPasta {

    @Override
    protected String definirPastaRoot() {
        return "imagens";
    }

}
