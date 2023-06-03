package com.efigueredo.file_storage.video_service.pastas;

import com.efigueredo.file_storage.shared.service.pastas.PastaDiscoServiceImpl;
import com.efigueredo.file_storage.shared.service.usuarios.UsuarioLogadoImpl;
import com.efigueredo.file_storage.video_service.util.ManipuladorPastaDisco;
import com.efigueredo.file_storage.video_service.util.VerificadorPastasDisco;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PastaDiscoServiceImplTest {

    private final long idUsuarioLogdao = new UsuarioLogadoImpl().obterIdUsuarioLogado();

    @Autowired
    private PastaDiscoServiceImpl pastaDiscoService;

    @Autowired
    private VerificadorPastasDisco verificadorPastasDisco;

    @Autowired
    private ManipuladorPastaDisco manipuladorPastaDisco;

    @AfterAll
    public static void afterAll() throws IOException {
        ManipuladorPastaDisco manipuladorPastaDisco1 = new ManipuladorPastaDisco();
        manipuladorPastaDisco1.removerPastaUsuario();
        manipuladorPastaDisco1.removerPastaRoot();
    }

    @Test
    @DisplayName("Deveria criar pasta quando pasta root existir.")
    public void criarPasta_cenario1() throws IOException {
        String nomePasta = "Pasta1";
        this.pastaDiscoService.criarPastaNoDisco(nomePasta);
        boolean pastaFoiCriada = this.verificadorPastasDisco.pastaDeNomeExiste(nomePasta);
        assertTrue(pastaFoiCriada);
        this.manipuladorPastaDisco.roolbackPastaCriada(nomePasta);
    }

    @Test
    @DisplayName("Deveria remover pasta do disco")
    public void removerPasta_cenario1() throws IOException {
        String nomePasta = "Pasta1";
        this.pastaDiscoService.criarPastaNoDisco(nomePasta);
        boolean pastaFoiCriada = this.verificadorPastasDisco.pastaDeNomeExiste(nomePasta);
        assertTrue(pastaFoiCriada);
        this.pastaDiscoService.removerPastaDoDisco(nomePasta);
        boolean pastaExiste = this.verificadorPastasDisco.pastaDeNomeExiste(nomePasta);
        assertFalse(pastaExiste);
    }

    @Test
    @DisplayName("Deveria alterar nome de pasta do disco")
    public void alterarNome_cenario1() throws IOException {
        String nomePasta = "Pasta1";
        String novoNomePasta = "Pasta2";
        this.pastaDiscoService.criarPastaNoDisco(nomePasta);
        boolean pastaFoiCriada = this.verificadorPastasDisco.pastaDeNomeExiste(nomePasta);
        boolean pastaDeNovoNomeExiste = this.verificadorPastasDisco.pastaDeNomeExiste(novoNomePasta);
        assertTrue(pastaFoiCriada);
        assertFalse(pastaDeNovoNomeExiste);
        this.pastaDiscoService.alterarNomePastaNoDisco(nomePasta, novoNomePasta);
        pastaDeNovoNomeExiste = this.verificadorPastasDisco.pastaDeNomeExiste(novoNomePasta);
        assertTrue(pastaDeNovoNomeExiste);
        this.manipuladorPastaDisco.roolbackPastaCriada(novoNomePasta);
    }

    @Test
    @DisplayName("Deveria remover todos os arquivos da pasta")
    public void limparPasta_cenario1() throws IOException {
        String nomePasta = "Pasta1";
        this.pastaDiscoService.criarPastaNoDisco(nomePasta);
        boolean pastaFoiCriada = this.verificadorPastasDisco.pastaDeNomeExiste(nomePasta);
        assertTrue(pastaFoiCriada);
        this.manipuladorPastaDisco.inserirArquivoNaPasta(nomePasta, "arquivo1.txt");
        this.manipuladorPastaDisco.inserirArquivoNaPasta(nomePasta, "arquivo2.txt");
        this.manipuladorPastaDisco.inserirArquivoNaPasta(nomePasta, "arquivo3.txt");
        int quantidadeArquivos = this.verificadorPastasDisco.obterQuantidadeDeArquivosPresentesNaPasta(nomePasta);
        assertEquals(3, quantidadeArquivos);
        this.pastaDiscoService.removerTodosFilesDaPasta(nomePasta);
        quantidadeArquivos = this.verificadorPastasDisco.obterQuantidadeDeArquivosPresentesNaPasta(nomePasta);
        assertEquals(quantidadeArquivos, 0);
        this.manipuladorPastaDisco.roolbackPastaCriada(nomePasta);
    }

}