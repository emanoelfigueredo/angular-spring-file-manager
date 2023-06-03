package com.efigueredo.file_storage.video_service.util;

import com.efigueredo.file_storage.shared.service.pastas.ResolvedorPathPasta;
import com.efigueredo.file_storage.shared.service.usuarios.UsuarioLogadoImpl;
import com.efigueredo.file_storage.video_service.service.video.ResolvedorPathVideos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ManipuladorPastaDisco {

    @Autowired
    public ResolvedorPathPasta resolvedorPathPasta;
    public final long idUsuarioLogado = new UsuarioLogadoImpl().obterIdUsuarioLogado();

    public ManipuladorPastaDisco() {
        this.resolvedorPathPasta = new ResolvedorPathVideos();
    }

    public Path roolbackPastaCriada(String nome) throws IOException {
        Path pathPasta = this.resolvedorPathPasta.obterPathPastaCriada(this.idUsuarioLogado, nome);
        Files.deleteIfExists(pathPasta);
        return pathPasta;
    }

    public void inserirArquivoNaPasta(String nomePasta, String file) throws IOException {
        Path pathPasta = this.resolvedorPathPasta.obterPathPastaCriada(this.idUsuarioLogado, nomePasta);
        Path pathArquivo = pathPasta.resolve(file);
        Files.createFile(pathArquivo);
    }

    public void removerPastaUsuario() throws IOException {
        Path pathPastaUsuario = this.resolvedorPathPasta.obterPathPastaUsuario(this.idUsuarioLogado);
        Files.deleteIfExists(pathPastaUsuario);
    }

    public void removerPastaRoot() throws IOException {
        Path pastaRoot = this.resolvedorPathPasta.getPastaRoot();
        Files.deleteIfExists(pastaRoot);
    }
}
