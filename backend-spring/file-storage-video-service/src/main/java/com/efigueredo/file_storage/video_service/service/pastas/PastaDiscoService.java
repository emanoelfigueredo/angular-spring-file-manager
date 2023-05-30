package com.efigueredo.file_storage.video_service.service.pastas;

import com.efigueredo.file_storage.video_service.domain.Pasta;

public interface PastaDiscoService {

    void criarPastaNoDisco(String nomePasta);
    void removerPastaDoDisco(String nomePasta);
    void alterarNomePastaNoDisco(String nomePasta, String novoNome);
    void criarPastaUsuarioNoDiscoCasoNaoExista(long idUsuarioLogado);
    void criarPastaRootNoDiscoCasoNaoExita();

}
