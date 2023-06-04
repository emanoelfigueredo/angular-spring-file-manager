package com.efigueredo.file_storage.arquivo_service.service;

import com.efigueredo.file_storage.shared.service.pastas.ResolvedorPathPasta;
import org.springframework.stereotype.Service;

@Service
public class ResolvedorPathPastaArquivos extends ResolvedorPathPasta {

    @Override
    protected String definirPastaRoot() {
        return "arquivos";
    }

}
