package com.efigueredo.file_storage.shared.service.files;


import com.efigueredo.file_storage.shared.domain.Arquivo;
import com.efigueredo.file_storage.shared.domain.PastaRepository;
import com.efigueredo.file_storage.shared.service.files.FileStorageDiscoService;
import com.efigueredo.file_storage.shared.service.pastas.ResolvedorPathPasta;
import com.efigueredo.file_storage.shared.service.usuarios.UsuarioLogado;
import com.efigueredo.file_storage.shared.service.usuarios.UsuarioLogadoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileStorageDiscoServiceImpl implements FileStorageDiscoService {

    @Autowired
    private ResolvedorPathPasta resolvedorPathPasta;

    @Autowired
    private PastaRepository pastaRepository;

    private final long idUsuarioLogado;

    public FileStorageDiscoServiceImpl() {
        UsuarioLogado usuarioLogado = new UsuarioLogadoImpl();
        this.idUsuarioLogado = usuarioLogado.obterIdUsuarioLogado();
    }

    @Override
    public Mono<Tuple2<FilePart, Mono<Arquivo>>> salvarFileNoDisco(Tuple2<FilePart, Mono<Arquivo>> tuple) {
        return tuple.getT2()
                .flatMap(this::obterPathVideo)
                .flatMap(pathVideo -> tuple.getT1().transferTo(pathVideo))
                .then(Mono.just(tuple));
    }

    @Override
    public Mono<Arquivo> removerFileDoDisco(Arquivo file) {
        Arquivo video = file;
        return Mono.just(video)
                .flatMap(this::obterPathVideo)
                .flatMap(pathVideo -> {
                    try {
                        Files.deleteIfExists(pathVideo);
                    } catch (Exception e) {

                    }
                    finally {
                        return Mono.just(video);
                }
                });
    }

    @Override
    public Mono<Resource> obterFileDoDisco(Arquivo file) {
        Arquivo video = file;
        return Mono.just(video)
                .flatMap(this::obterPathVideo)
                .flatMap(this::obterResource);
    }

    private Mono<Resource> obterResource(Path path) {
            try {
                Resource resource = new UrlResource(path.toUri());
                return Mono.just(resource);
            } catch (Exception e) {
                throw new RuntimeException();
            }
    }

    private Mono<Path> obterPathVideo(Arquivo file) {
        Arquivo video = file;
        return this.pastaRepository.findByIdUsuarioAndId(this.idUsuarioLogado, video.getIdPasta())
                .map(pasta -> this.resolvedorPathPasta
                        .obterPathFile(this.idUsuarioLogado, pasta.getNome(), video.getId(), video.getExtencao()));
    }

}
