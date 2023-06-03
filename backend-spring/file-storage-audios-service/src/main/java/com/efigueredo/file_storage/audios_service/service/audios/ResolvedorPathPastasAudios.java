package com.efigueredo.file_storage.audios_service.service.audios;

import com.efigueredo.file_storage.shared.service.pastas.ResolvedorPathPasta;

public class ResolvedorPathPastasAudios extends ResolvedorPathPasta {

    @Override
    protected String definirPastaRoot() {
        return "audios";
    }

}
