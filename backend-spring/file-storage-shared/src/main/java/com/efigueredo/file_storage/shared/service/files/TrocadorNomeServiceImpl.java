package com.efigueredo.file_storage.shared.service.files;

import com.efigueredo.file_storage.shared.domain.ArquivoRepository;
import com.efigueredo.file_storage.shared.service.dto.FileStorageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TrocadorNomeServiceImpl implements TrocadorNomeService {

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Override
    public Mono<FileStorageDto> trocarNomeCasoJaExista(FileStorageDto upload) {
        return this.arquivoRepository.existsByNomeAndIdPasta(upload.getNome(), upload.getIdPasta())
                .flatMap(existe -> {
                    if(existe) {
                        return this.alterarNomeSeJaExistir(upload);
                    }
                    return Mono.just(upload);
                })
                .flatMap(e ->  Mono.just(upload));
    }

    private Mono<FileStorageDto> alterarNomeSeJaExistir(FileStorageDto dtoUploadArquivo) {
        String nomeCompleto = dtoUploadArquivo.getNome();
        int indexPonto = nomeCompleto.lastIndexOf(".");
        String nome = nomeCompleto.substring(0, indexPonto);
        return this.arquivoRepository.countByIdPastaAndNomeContaining(dtoUploadArquivo.getIdPasta(), nome)
                .flatMap(quantidade -> {
                    if(quantidade > 0) {
                        System.out.println(quantidade);
                        this.inserirParentesesQuantidadeNoNome(dtoUploadArquivo, quantidade, indexPonto);
                    }
                    return Mono.just(dtoUploadArquivo);
                });
    }

    private void inserirParentesesQuantidadeNoNome(FileStorageDto dtoUploadArquivo, long quantidade, int indexPonto) {
        String nome = dtoUploadArquivo.getNome();
        String nomeArquivo = nome.substring(0, indexPonto);
        String extencaoArquivo = nome.substring(indexPonto);
        String novoNome = nomeArquivo + "(" + (quantidade + 1) + ")" + extencaoArquivo;
        dtoUploadArquivo.setNome(novoNome);
    }
}
