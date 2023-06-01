package com.efigueredo.file_storage.shared.service.pastas;

import com.efigueredo.file_storage.shared.domain.FileStorageArquivo;
import com.efigueredo.file_storage.shared.domain.Pasta;
import com.efigueredo.file_storage.shared.service.usuarios.UsuarioLogadoImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@Slf4j
public class PastaServiceImpl extends PastaService {

    private final long idUsuarioLogado;

    public PastaServiceImpl() {
        this.idUsuarioLogado = new UsuarioLogadoImpl().obterIdUsuarioLogado();
    }

    @Override
    public Mono<Pasta> criarPasta(Mono<String> nomePasta) {
        return nomePasta
                .map(nome -> new Pasta(null, this.idUsuarioLogado, nome, false, 0, 0L))
                .flatMap(super.pastaRepository::insert);
    }

    @Override
    public Mono<String> removerPasta(String nomePasta) {
        return super.verificadorPastas.lancarExcecaoQuandoPastaDeUsuarioNaoExistirNome(idUsuarioLogado, nomePasta)
                .flatMap(nome -> super.pastaRepository.findByIdUsuarioAndNome(this.idUsuarioLogado, nomePasta))
                .flatMap(super.pastaRepository::delete)
                .then(Mono.just(nomePasta));
    }

    @Override
    public Mono<Pasta> atualizarPasta(String nomePasta, String novoNome) {
        return super.verificadorPastas.lancarExcecaoQuandoPastaDeUsuarioExistirNome(this.idUsuarioLogado, novoNome)
                .flatMap(novoNomeVerificado -> super.verificadorPastas
                        .lancarExcecaoQuandoPastaDeUsuarioNaoExistirNome(idUsuarioLogado, nomePasta))
                .flatMap(nome -> super.pastaRepository.findByIdUsuarioAndNome(idUsuarioLogado, nomePasta))
                .doOnNext(pasta -> pasta.setNome(novoNome))
                .flatMap(super.pastaRepository::save);
    }

    @Override
    public Flux<Pasta> listarPastasDoUsuario(int size, int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "favorito");
        return super.pastaRepository.findAllByIdUsuario(this.idUsuarioLogado, sort);
    }

    @Override
    public Mono<Pasta> obterPastaDoUsuario(Mono<String> idPasta) {
        return idPasta
                .flatMap(id -> super.verificadorPastas.lancarExcecaoQuandoPastaDeUsuarioNaoExistirId(this.idUsuarioLogado, id))
                .flatMap(id -> super.pastaRepository.findByIdUsuarioAndId(this.idUsuarioLogado, id));
    }

    @Override
    public Mono<Void> mudarEstadoFavorito(Mono<String> idPasta) {
        return idPasta
                .flatMap(id -> super.verificadorPastas.lancarExcecaoQuandoPastaDeUsuarioNaoExistirId(this.idUsuarioLogado, id))
                .flatMap(id -> super.pastaRepository.findByIdUsuarioAndId(this.idUsuarioLogado, id))
                .flatMap(pasta -> {
                    pasta.trocarFavorito();
                    return super.pastaRepository.save(pasta);
                })
                .then(Mono.empty());
    }

    @Override
    public Mono<FileStorageArquivo> aumentarTamanhoDaPasta(FileStorageArquivo file) {
        return this.obterPastaParaFile(file)
                .flatMap(pasta -> {
                    pasta.contabilizarSomaTamanho(file.getTamanho());
                    return this.pastaRepository.save(pasta);
                })
                .then(Mono.just(file));
    }

    @Override
    public Mono<FileStorageArquivo> diminuirTamanhoDaPasta(FileStorageArquivo file) {
       return this.obterPastaParaFile(file)
                .flatMap(pasta -> {
                    pasta.contabilizarSubtracaoTamanho(file.getTamanho());
                    return this.pastaRepository.save(pasta);
                })
               .then(Mono.just(file));
    }

    @Override
    public Mono<FileStorageArquivo> contabilizarAdicaoArquivoNaPasta(FileStorageArquivo file) {
        return this.obterPastaParaFile(file)
                .flatMap(pasta -> {
                    pasta.somarNovoArquivo();
                    return this.pastaRepository.save(pasta);
                })
                .then(Mono.just(file));
    }

    @Override
    public Mono<FileStorageArquivo> contabilizarRemocaoArquivoNaPasta(FileStorageArquivo file) {
        return this.obterPastaParaFile(file)
                .flatMap(pasta -> {
                    pasta.subtrairRemocaoArquivo();
                    return this.pastaRepository.save(pasta);
                })
                .then(Mono.just(file));
    }

    @Override
    public Mono<Pasta> removerTodosArquivosDaPasta(String idPasta) {
        return this.obterPastaDoUsuario(Mono.just(idPasta))
                .flatMap(pasta -> {
                    pasta.zerarPasta();
                    return this.pastaRepository.save(pasta);
                });
    }

    private Mono<Pasta> obterPastaParaFile(FileStorageArquivo file) {
        return Mono.just(file)
                .flatMap(file1 -> super.verificadorPastas
                        .lancarExcecaoQuandoPastaDeUsuarioNaoExistirId(this.idUsuarioLogado, file.getIdPasta()))
                .flatMap(id -> this.pastaRepository.findByIdUsuarioAndId(this.idUsuarioLogado, id));
    }

}
