package com.efigueredo.file_storage.video_service.util;

import com.efigueredo.file_storage.shared.domain.PastaRepository;
import com.efigueredo.file_storage.shared.service.usuarios.UsuarioLogadoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Service
public class ManipuladorPastasBancoDeDados {

    private final long idUsuarioLogado = new UsuarioLogadoImpl().obterIdUsuarioLogado();

    @Autowired
    private PastaRepository pastaRepository;

    public boolean pastaDeNomeExisteNoBanco(String nome) {
        return this.pastaRepository.existsByIdUsuarioAndNome(this.idUsuarioLogado, nome)
                .block();
    }

}
