package com.efigueredo.file_storage.video_service.service.videos;

import org.springframework.http.codec.multipart.FilePart;

public interface VideosDiscoService {

    void salvarVideoNoDisco(FilePart video);
    void removerVideoDoDisco(String idVideo);
    void alterarNomeVideoNoDisco(String idVideo, String novoNome);

}
