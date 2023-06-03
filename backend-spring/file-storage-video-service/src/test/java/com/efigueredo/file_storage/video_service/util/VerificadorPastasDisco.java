package com.efigueredo.file_storage.video_service.util;

import com.efigueredo.file_storage.shared.service.pastas.ResolvedorPathPasta;
import com.efigueredo.file_storage.shared.service.usuarios.UsuarioLogadoImpl;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Service
public class VerificadorPastasDisco {

    @Autowired
    private ResolvedorPathPasta resolvedorPathPasta;
    private final long idUsuarioLogado = new UsuarioLogadoImpl().obterIdUsuarioLogado();

    public boolean pastaDeNomeExiste(String nome) throws IOException {
        Path pathPasta = this.resolvedorPathPasta.obterPathPastaCriada(this.idUsuarioLogado, nome);
        File pasta = pathPasta.toFile();
        return pasta.exists();
    }

    public int obterQuantidadeDeArquivosPresentesNaPasta(String nomePasta) throws IOException {
        Path pathPasta = this.resolvedorPathPasta.obterPathPastaCriada(this.idUsuarioLogado, nomePasta);
        Collection<File> files = FileUtils.listFiles(pathPasta.toFile(), new String[]{"txt"}, false);
        return files.size();
    }
}
