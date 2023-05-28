package com.efigueredo.file_storage.video_service.service.pastas;

import lombok.Data;
import lombok.Getter;

import java.nio.file.Path;
import java.nio.file.Paths;

@Getter
public abstract class ResolvedorPathPasta {

    protected Path pastaRoot;
    protected String templatePastaUsuario;
    protected String templatePastasCriadas;

    public ResolvedorPathPasta() {
        this.pastaRoot = Paths.get(this.definirPastaRoot());
        this.templatePastaUsuario = this.definirTemplatePastaUsuario();
        this.templatePastasCriadas = this.definirTemplatePastasCriadas();
    }

    protected abstract String definirPastaRoot();
    protected abstract String definirTemplatePastaUsuario();
    protected abstract String definirTemplatePastasCriadas();

    public Path obterPathPastaUsuario(Long idUsuario) {
        return pastaRoot.resolve(String.format(this.templatePastaUsuario, idUsuario));
    }

    public Path obterPathPastaCriada(Long idUsuario, String idPasta) {
        return this.obterPathPastaUsuario(idUsuario)
                .resolve(String.format(this.templatePastasCriadas, idPasta));
    }

}
