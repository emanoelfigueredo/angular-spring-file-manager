package com.efigueredo.file_storage.shared.service.pastas;

import reactor.core.publisher.Mono;

public interface PastaDiscoService {

    void criarPastaNoDisco(String nomePasta);
    void removerPastaDoDisco(String nomePasta);
    void alterarNomePastaNoDisco(String nomePasta, String novoNome);
    void criarPastaUsuarioNoDiscoCasoNaoExista(long idUsuarioLogado);
    void criarPastaRootNoDiscoCasoNaoExita();
    void removerTodosFilesDaPasta(String nomePasta);

}
