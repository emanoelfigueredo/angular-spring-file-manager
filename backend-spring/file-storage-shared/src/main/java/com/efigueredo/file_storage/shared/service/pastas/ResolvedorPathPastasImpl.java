package com.efigueredo.file_storage.shared.service.pastas;

public class ResolvedorPathPastasImpl extends ResolvedorPathPasta {

    @Override
    protected String definirPastaRoot() {
        return "videos";
    }

    @Override
    protected String definirTemplatePastaUsuario() {
        return "usuario_%d";
    }

    @Override
    protected String definirTemplatePastasCriadas() {
        return "pasta_%s";
    }

}
