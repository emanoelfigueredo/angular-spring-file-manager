package com.efigueredo.file_storage.video_service.service.pastas;

import com.efigueredo.file_storage.video_service.domain.Pasta;
import com.efigueredo.file_storage.video_service.infra.excetion.FileStorageException;
import com.efigueredo.file_storage.video_service.service.usuarios.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PastaDiscoServiceImpl implements PastaDiscoService {

    @Autowired
    private ResolvedorPathPasta resolvedorPathPasta;

    @Autowired
    private UsuarioLogado usuarioLogado;

    @Override
    public void criarPastaNoDisco(Pasta pasta) {
        long idUsuarioLogado = this.usuarioLogado.obterIdUsuarioLogado();
        try {
            Path pathPastaCriada = this.resolvedorPathPasta
                    .obterPathPastaCriada(idUsuarioLogado, pasta.getId());
            Files.createDirectory(pathPastaCriada);
        } catch (IOException e) {
            this.criarPastaRootNoDiscoCasoNaoExita(pasta);
            this.criarPastaUsuarioNoDiscoCasoNaoExista(idUsuarioLogado, pasta);
            try {
                this.criarPastaNoDisco(pasta);
            } catch (Exception e2) {
                e.printStackTrace();
                throw new FileStorageException("Falha ao criar pasta", "Falha ao criar pasta de id " + pasta.getId()
                        + ", nome  " + pasta.getNome() + " no disco. Tente novamente em alguns instantes. Caso o erro "
                        + "persista, contate desenvolvedor: emanoel.figueredo.1@gmail.com", "", 500, pasta.getId());
            }
        }
    }

    @Override
    public void removerPastaDoDisco(Pasta pasta) {
        try {
            Path pathPastaCriada = this.resolvedorPathPasta
                    .obterPathPastaCriada(this.usuarioLogado.obterIdUsuarioLogado(), pasta.getId());
            Files.delete(pathPastaCriada);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileStorageException("Falha ao remover pasta", "Falha ao remover pasta de id " + pasta.getId()
                    + ", nome  " + pasta.getNome() + " no disco. Tente novamente em alguns instantes. Caso o erro "
                    + "persista, contate o desenvolvedor: emanoel.figueredo.1@gmail.com", "", 500, pasta.getId());
        }
    }

    @Override
    public void criarPastaUsuarioNoDiscoCasoNaoExista(long idUsuarioLogado, Pasta pasta) {
        try {
            Path pathPastaUsuario = this.resolvedorPathPasta.obterPathPastaUsuario(idUsuarioLogado);
            Files.createDirectory(pathPastaUsuario);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileStorageException("Falha ao criar pasta do usuário no sistema de armazenamento do sistema.",
                    "Tente novamente, caso o erro contate desenvolvedor: emanoel.figueredo.1@gmail.com", "", 500, pasta.getId());
        }
    }

    @Override
    public void criarPastaRootNoDiscoCasoNaoExita(Pasta pasta) {
        try {
            Path pathPastaRoot = this.resolvedorPathPasta.getPastaRoot();
            Files.createDirectory(pathPastaRoot);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileStorageException("Falha ao criar pasta do root no sistema de armazenamento do sistema.",
                    "Tente novamente, caso o erro contate desenvolvedor: emanoel.figueredo.1@gmail.com", "", 500, pasta.getId());
        }
    }

}
