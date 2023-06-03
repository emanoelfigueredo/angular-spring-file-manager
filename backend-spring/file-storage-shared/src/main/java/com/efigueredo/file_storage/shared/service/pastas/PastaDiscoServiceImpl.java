package com.efigueredo.file_storage.shared.service.pastas;

import com.efigueredo.file_storage.shared.domain.Pasta;
import com.efigueredo.file_storage.shared.domain.PastaRepository;
import com.efigueredo.file_storage.shared.infra.exception.FileStorageException;
import com.efigueredo.file_storage.shared.service.ServicesUtils;
import com.efigueredo.file_storage.shared.service.usuarios.UsuarioLogadoImpl;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PastaDiscoServiceImpl implements PastaDiscoService {

    @Autowired
    private ResolvedorPathPasta resolvedorPathPasta;

    @Autowired
    private PastaRepository pastaRepository;

    @Autowired
    private ServicesUtils<Pasta> servicesUtils;

    private final long idUsuarioLogado;

    public PastaDiscoServiceImpl() {
        this.idUsuarioLogado = new UsuarioLogadoImpl().obterIdUsuarioLogado();
    }

    @Override
    public void criarPastaNoDisco(String nomePasta) {
        try {
            Path pathPastaCriada = this.resolvedorPathPasta
                    .obterPathPastaCriada(this.idUsuarioLogado, nomePasta);
            Files.createDirectory(pathPastaCriada);
        } catch (IOException e) {
            this.criarPastaRootNoDiscoCasoNaoExita();
            this.criarPastaUsuarioNoDiscoCasoNaoExista(idUsuarioLogado);
            try {
                this.criarPastaNoDisco(nomePasta);
            } catch (Exception e2) {
                e.printStackTrace();
                throw new FileStorageException("Falha ao criar pasta", "Falha ao criar pasta de nome " + nomePasta
                        + " no disco. Tente novamente em alguns instantes. Caso o erro "
                        + "persista, contate desenvolvedor: emanoel.figueredo.1@gmail.com", "", 500);
            }
        }
    }

    @Override
    public void removerPastaDoDisco(String nome) {
        try {
            Path pathPastaCriada = this.resolvedorPathPasta
                    .obterPathPastaCriada(this.idUsuarioLogado, nome);
            Files.delete(pathPastaCriada);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileStorageException("Falha ao remover pasta", "Falha ao remover pasta de nome " + nome
                    + " no disco. Tente novamente em alguns instantes. Caso o erro "
                    + "persista, contate o desenvolvedor: emanoel.figueredo.1@gmail.com", "", 500);
        }
    }

    @Override
    public void alterarNomePastaNoDisco(String nomePasta, String novoNome) {
        try {
            Path pathPastaCriada = this.resolvedorPathPasta
                    .obterPathPastaCriada(this.idUsuarioLogado, nomePasta);
            Path pathPastaNomeAlterado = this.resolvedorPathPasta
                    .obterPathPastaCriada(this.idUsuarioLogado, novoNome);
            Files.move(pathPastaCriada, pathPastaNomeAlterado);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileStorageException("Falha ao alterar nome de pasta", "Falha ao alterar nome de pasta, com" +
                    " nome " + nomePasta + ". Tente novamente em alguns instantes. Caso o erro "
                    + "persista, contate o desenvolvedor: emanoel.figueredo.1@gmail.com", "", 500);
        }
    }

    @Override
    public void criarPastaUsuarioNoDiscoCasoNaoExista(long idUsuarioLogado) {
        try {
            Path pathPastaUsuario = this.resolvedorPathPasta.obterPathPastaUsuario(idUsuarioLogado);
            Files.createDirectory(pathPastaUsuario);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileStorageException("Falha ao criar pasta do usuário no sistema de armazenamento do sistema.",
                    "Tente novamente, caso o erro contate desenvolvedor: emanoel.figueredo.1@gmail.com", "", 500);
        }
    }

    @Override
    public void criarPastaRootNoDiscoCasoNaoExita() {
        try {
            Path pathPastaRoot = this.resolvedorPathPasta.getPastaRoot();
            Files.createDirectory(pathPastaRoot);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileStorageException("Falha ao criar pasta do root no sistema de armazenamento do sistema.",
                    "Tente novamente, caso o erro contate desenvolvedor: emanoel.figueredo.1@gmail.com", "", 500);
        }
    }

    @Override
    public void removerTodosFilesDaPasta(String nomePasta) {
        Path pathPasta = this.resolvedorPathPasta
                .obterPathPastaCriada(this.idUsuarioLogado, nomePasta);
        try {
            FileUtils.cleanDirectory(pathPasta.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
