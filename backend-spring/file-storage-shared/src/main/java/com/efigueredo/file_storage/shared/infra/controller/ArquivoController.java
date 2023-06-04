package com.efigueredo.file_storage.shared.infra.controller;

import com.efigueredo.file_storage.shared.domain.Arquivo;
import com.efigueredo.file_storage.shared.domain.PastaRepository;
import com.efigueredo.file_storage.shared.service.dto.DeleteListFilesDto;
import com.efigueredo.file_storage.shared.service.files.ArquivoService;
import com.efigueredo.file_storage.shared.service.pastas.PastaDiscoService;
import com.efigueredo.file_storage.shared.service.ConvertedorStringJsonParaDto;
import com.efigueredo.file_storage.shared.service.files.FileStorageDiscoService;
import com.efigueredo.file_storage.shared.service.files.VerificadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ArquivoController {

    @Autowired
    private ArquivoService fileService;

    @Autowired
    private VerificadorService verificadorService;

    @Autowired
    private ConvertedorStringJsonParaDto convertedorStringJsonParaDto;

    @Autowired
    private PastaRepository pastaRepository;

    @Autowired
    private FileStorageDiscoService discoService;

    @Autowired
    private PastaDiscoService pastaDiscoService;

    @GetMapping("pasta/{idPasta}")
    public Flux<Arquivo> listarArquivosDoUsuario(@PathVariable String idPasta) {
        return this.fileService.listarArquivosDoUsuario(idPasta);
    }

    @GetMapping("{id}")
    public Mono<Resource> obterArquivos(@PathVariable String id) {
        return this.fileService.obterArquivoDoUsuario(id)
                .flatMap(this.discoService::obterFileDoDisco);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<?> uploadArquivos(@RequestPart("arquivos") Flux<FilePart> arquivos,
                                            @RequestPart("dados") Flux<String> dadosVideos) {
        return Flux.zip(arquivos, dadosVideos)
                .map(upload -> upload.mapT2(this.convertedorStringJsonParaDto::converterStringParaDtoUpload))
                .map(upload -> upload.mapT2(this.fileService::uploadArquivo))
                .flatMap(this.discoService::salvarFileNoDisco)
                .then(Mono.empty());
    }

    @PatchMapping("{id}")
    public Mono<Void> alteararNomeArquivo(@PathVariable String id,
                                       @RequestParam("novoNome") String novoNome) {
        return this.fileService.alterarNomeArquivoDoUsuario(id, novoNome)
                .then(Mono.empty());
    }

    @DeleteMapping("{id}")
    public Mono<Void> removerArquivos(@PathVariable String id) {
        return this.fileService.obterArquivoDoUsuario(id)
                .flatMap(this.discoService::removerFileDoDisco)
                .flatMap(arquivo -> this.fileService.removerArquivoDoUsuario(arquivo))
                .then(Mono.empty());
    }

    @DeleteMapping("all/{nomePasta}")
    public Mono<Void> removerTodosOsArquivosDaPasta(@PathVariable String nomePasta) {
        return this.fileService.removerTodosOsArquivosDaPasta(nomePasta)
                .flatMap(nome -> {
                    this.pastaDiscoService.removerTodosFilesDaPasta(nomePasta);
                    return Mono.empty();
                });
    }

    @DeleteMapping("list")
    public Flux<Void> removerMuitosArquivos(@RequestBody DeleteListFilesDto ids) {
        return Flux.fromStream(ids.getListaIdFiles().stream())
                .flatMap(this::removerArquivos);
    }

    @PatchMapping("inverter-favorito/{id}")
    public Mono<Void> inverterFavorito(@PathVariable String id) {
        return this.fileService.inverterValorFavoritoFile(id);
    }

}
