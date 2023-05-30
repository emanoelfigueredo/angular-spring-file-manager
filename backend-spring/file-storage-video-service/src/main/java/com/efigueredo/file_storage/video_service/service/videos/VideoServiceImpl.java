package com.efigueredo.file_storage.video_service.service.videos;

import com.efigueredo.file_storage.video_service.domain.Video;
import com.efigueredo.file_storage.video_service.domain.VideoRepository;
import com.efigueredo.file_storage.video_service.infra.excetion.FileStorageException;
import com.efigueredo.file_storage.video_service.service.dto.DtoUploadVideo;
import com.efigueredo.file_storage.video_service.service.usuarios.UsuarioLogado;
import com.efigueredo.file_storage.video_service.service.usuarios.UsuarioLogadoImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.LocalDateTime;

public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VerificadorVideos verificadorVideos;

    @Autowired
    private TrocadorNomeVideos trocadorNomeVideos;

    @Autowired
    private ModelMapper modelMapper;

    private final long idUsuarioLogado;

    public VideoServiceImpl() {
        UsuarioLogado usuarioLogado = new UsuarioLogadoImpl();
        this.idUsuarioLogado = usuarioLogado.obterIdUsuarioLogado();
    }

    @Override
    public Flux<Video> listarVideosDoUsuario() {
        return this.videoRepository.findAllByIdUsuarioOrderByMomentoUploadDesc(this.idUsuarioLogado);
    }

    @Override
    public Flux<Video> uploadVideos(DtoUploadVideo dtoUploadVideosMono) {
        return Flux.just(dtoUploadVideosMono)
                .flatMap(this.trocadorNomeVideos::trocarNomeCasoJaExista)
                .flatMap(this::salvarVideoNoBancoDeDados);
    }

    @Override
    public Mono<Void> alterarNomeVideoDoUsuario(String idVideo, String novoNome) {
        return this.verificadorVideos.lancarExcecaoQuandoVideoDeIdNaoExistir(idVideo)
                .flatMap(id -> this.videoRepository.findByIdAndIdUsuario(idVideo, this.idUsuarioLogado))
                .zipWhen(video -> this.obterDtoUpladComNomeAdequado(video, novoNome))
                .flatMap(this::trocarNomeVideo)
                .then(Mono.empty());
    }

    @Override
    public Mono<Void> removerVideoDoUsuario(String idVideo) {
        return this.verificadorVideos.lancarExcecaoQuandoVideoDeIdNaoExistir(idVideo)
                .flatMap(id -> this.videoRepository.deleteByIdAndIdUsuario(idVideo, this.idUsuarioLogado))
                .then(Mono.empty());
    }

    private Mono<Video> salvarVideoNoBancoDeDados(DtoUploadVideo dto) {
        System.out.println(dto);
        Video video = this.modelMapper.map(dto, Video.class);
        video.setFavorito(false);
        video.setIdUsuario(this.idUsuarioLogado);
        video.setMomentoUpload(LocalDateTime.now());
        video.setId(null);
        return this.videoRepository.save(video);
    }

    private Mono<?> trocarNomeVideo(Tuple2<Video, DtoUploadVideo> tuple) {
        Video video = tuple.getT1();
        DtoUploadVideo dados = tuple.getT2();
        if(this.nomePodeSerTrocado(video, dados)) {
            System.out.println("entrou");
            video.setNome(dados.getNome());
            return this.videoRepository.save(video);
        }
        return Mono.empty();
    }

    private boolean nomePodeSerTrocado(Video video, DtoUploadVideo dados) {
        String nomeCompletoVideo = video.getNome();
        String nomeCompletoDados = dados.getNome();
        String nomeVideo = nomeCompletoVideo.substring(0, nomeCompletoVideo.lastIndexOf(".") - 3);
        String nomeDados = nomeCompletoDados.substring(0, nomeCompletoDados.lastIndexOf(".") - 3);
        return !nomeVideo.equals(nomeDados);
    }

    private Mono<DtoUploadVideo> obterDtoUpladComNomeAdequado(Video video, String novoNome) {
        DtoUploadVideo dto = this.modelMapper.map(video, DtoUploadVideo.class);
        dto.setNome(novoNome);
        return this.trocadorNomeVideos.trocarNomeCasoJaExista(dto);
    }

}
