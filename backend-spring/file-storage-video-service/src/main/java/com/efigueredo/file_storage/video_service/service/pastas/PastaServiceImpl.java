package com.efigueredo.file_storage.video_service.service.pastas;

import com.efigueredo.file_storage.video_service.domain.Pasta;
import com.efigueredo.file_storage.video_service.service.usuarios.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class PastaServiceImpl extends PastaService {

    @Autowired
    private UsuarioLogado usuarioLogado;

    @Override
    public Mono<Pasta> criarPasta(Mono<String> nomePasta) {
        long idUsuarioLogado = this.usuarioLogado.obterIdUsuarioLogado();
        return nomePasta
                .flatMap(nome -> super.verificadorPastas.lancarExcecaoQuandoPastaDeUsuarioExistirNome(idUsuarioLogado, nome))
                .map(nome -> new Pasta(null, idUsuarioLogado, nome, false, 0, 0L))
                .flatMap(super.pastaRepository::insert);
    }

    @Override
    public Mono<Pasta> removerPasta(Mono<String> idPasta) {
        long idUsuarioLogado = this.usuarioLogado.obterIdUsuarioLogado();
        return idPasta
                .flatMap(id -> super.verificadorPastas.lancarExcecaoQuandoPastaDeUsuarioNaoExistirId(idUsuarioLogado, id))
                .flatMap(id -> super.pastaRepository.findByIdUsuarioAndId(idUsuarioLogado, id))
                .doOnNext(super.pastaRepository::delete);
    }

    @Override
    public Mono<Pasta> atualizarPasta(Mono<String> idPasta, String novoNome) {
        long idUsuarioLogado = this.usuarioLogado.obterIdUsuarioLogado();
        return idPasta
                .flatMap(id -> super.verificadorPastas.lancarExcecaoQuandoPastaDeUsuarioExistirNome(idUsuarioLogado, novoNome))
                .flatMap(nome -> idPasta.flatMap(id -> super.verificadorPastas.lancarExcecaoQuandoPastaDeUsuarioNaoExistirId(idUsuarioLogado, id)))
                .flatMap(id -> super.pastaRepository.findByIdUsuarioAndId(idUsuarioLogado, id))
                .doOnNext(pasta -> pasta.setNome(novoNome))
                .flatMap(super.pastaRepository::save);
    }

    @Override
    public Flux<Pasta> listarPastasDoUsuario(int size, int page) {
        long idUsuarioLogado = this.usuarioLogado.obterIdUsuarioLogado();
        Sort sort = Sort.by(Sort.Direction.DESC, "favorito");
        return super.pastaRepository.findAllByIdUsuario(idUsuarioLogado, sort);
    }

    @Override
    public Mono<Pasta> obterPastaDoUsuario(Mono<String> idPasta) {
        long idUsuarioLogado = this.usuarioLogado.obterIdUsuarioLogado();
        return idPasta
                .flatMap(id -> super.verificadorPastas.lancarExcecaoQuandoPastaDeUsuarioNaoExistirId(idUsuarioLogado, id))
                .flatMap(id -> super.pastaRepository.findByIdUsuarioAndId(idUsuarioLogado, id));
    }

    @Override
    public Mono<Void> mudarEstadoFavorito(Mono<String> idPasta) {
        long idUsuarioLogado = this.usuarioLogado.obterIdUsuarioLogado();
        return idPasta
                .flatMap(id -> super.verificadorPastas.lancarExcecaoQuandoPastaDeUsuarioNaoExistirId(idUsuarioLogado, id))
                .flatMap(id -> super.pastaRepository.findByIdUsuarioAndId(idUsuarioLogado, id))
                .flatMap(pasta -> {
                    pasta.trocarFavorito();
                    return super.pastaRepository.save(pasta);
                })
                .then(Mono.empty());
    }

}
