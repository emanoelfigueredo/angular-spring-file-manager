package com.efigueredo.file_storage.shared.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document("pastas")
public class Pasta {

    @Id
    private String id;
    private long idUsuario;
    private String nome;
    private boolean favorito;
    private int quatidadeArquivos;
    private long tamanho;

    public void trocarFavorito() {
        this.favorito = !this.favorito;
    }

    public void somarNovoArquivo() {
        this.quatidadeArquivos++;
    }

    public void subtrairRemocaoArquivo() {
        this.quatidadeArquivos--;
    }

    public void contabilizarSomaTamanho(long tamanhoArquivo) {
        this.tamanho += tamanhoArquivo;
    }

    public void contabilizarSubtracaoTamanho(long tamanhoArquivo) {
        this.tamanho -= tamanhoArquivo;
    }

    public void zerarPasta() {
        this.tamanho = 0;
        this.quatidadeArquivos = 0;
    }
}
