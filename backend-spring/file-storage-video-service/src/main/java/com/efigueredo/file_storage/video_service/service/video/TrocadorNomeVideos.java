package com.efigueredo.file_storage.video_service.service.video;

import com.efigueredo.file_storage.video_service.domain.VideoRepository;
import com.efigueredo.file_storage.shared.service.dto.FileStorageDto;
import com.efigueredo.file_storage.shared.service.files.TrocadorNomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TrocadorNomeVideos implements TrocadorNomeService {

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public Mono<FileStorageDto> trocarNomeCasoJaExista(FileStorageDto upload) {
        return this.videoRepository.existsByNomeAndIdPasta(upload.getNome(), upload.getIdPasta())
                .flatMap(existe -> {
                    if(existe) {
                        return this.alterarNomeSeJaExistir(upload);
                    }
                    return Mono.just(upload);
                })
                .flatMap(e ->  Mono.just(upload));
    }

    private Mono<FileStorageDto> alterarNomeSeJaExistir(FileStorageDto dtoUploadVideo) {
        String nomeCompleto = dtoUploadVideo.getNome();
        int indexPonto = nomeCompleto.lastIndexOf(".");
        String nome = nomeCompleto.substring(0, indexPonto);
        return this.videoRepository.countByIdPastaAndNomeContaining(dtoUploadVideo.getIdPasta(), nome)
                .flatMap(quantidade -> {
                    if(quantidade > 0) {
                        System.out.println(quantidade);
                        this.inserirParentesesQuantidadeNoNome(dtoUploadVideo, quantidade, indexPonto);
                    }
                    return Mono.just(dtoUploadVideo);
                });
    }

    private void inserirParentesesQuantidadeNoNome(FileStorageDto dtoUploadVideo, long quantidade, int indexPonto) {
        String nome = dtoUploadVideo.getNome();
        String nomeVideo = nome.substring(0, indexPonto);
        String extencaoVideo = nome.substring(indexPonto);
        String novoNome = nomeVideo + "(" + (quantidade + 1) + ")" + extencaoVideo;
        dtoUploadVideo.setNome(novoNome);
    }
}
