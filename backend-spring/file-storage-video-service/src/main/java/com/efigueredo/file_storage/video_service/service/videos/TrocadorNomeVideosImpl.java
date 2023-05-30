package com.efigueredo.file_storage.video_service.service.videos;

import com.efigueredo.file_storage.video_service.domain.Video;
import com.efigueredo.file_storage.video_service.domain.VideoRepository;
import com.efigueredo.file_storage.video_service.service.dto.DtoUploadVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TrocadorNomeVideosImpl implements TrocadorNomeVideos {

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public Mono<DtoUploadVideo> trocarNomeCasoJaExista(DtoUploadVideo upload) {
        return this.videoRepository.existsByNomeAndIdPasta(upload.getNome(), upload.getIdPasta())
                .flatMap(existe -> {
                    if(existe) {
                        return this.alterarNomeSeJaExistir(upload);
                    }
                    return Mono.just(upload);
                })
                .flatMap(e ->  Mono.just(upload));
    }

    private Mono<DtoUploadVideo> alterarNomeSeJaExistir(DtoUploadVideo dtoUploadVideo) {
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

    private void inserirParentesesQuantidadeNoNome(DtoUploadVideo dtoUploadVideo, long quantidade, int indexPonto) {
        String nome = dtoUploadVideo.getNome();
        String nomeVideo = nome.substring(0, indexPonto);
        String extencaoVideo = nome.substring(indexPonto);
        String novoNome = nomeVideo + "(" + (quantidade + 1) + ")" + extencaoVideo;
        dtoUploadVideo.setNome(novoNome);
    }

}
