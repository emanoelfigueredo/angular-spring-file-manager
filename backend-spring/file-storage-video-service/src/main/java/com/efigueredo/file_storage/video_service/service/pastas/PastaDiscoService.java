package com.efigueredo.file_storage.video_service.service.pastas;

import com.efigueredo.file_storage.video_service.domain.Pasta;

public interface PastaDiscoService {

    void criarPastaNoDisco(Pasta pasta);
    void removerPastaDoDisco(Pasta pasta);
    void criarPastaUsuarioNoDiscoCasoNaoExista(long idUsuarioLogado, Pasta pasta);
    void criarPastaRootNoDiscoCasoNaoExita(Pasta pasta);

}
