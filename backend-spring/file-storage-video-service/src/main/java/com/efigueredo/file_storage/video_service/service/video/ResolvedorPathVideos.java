package com.efigueredo.file_storage.video_service.service.video;

import com.efigueredo.file_storage.shared.service.pastas.ResolvedorPathPasta;

public class ResolvedorPathVideos extends ResolvedorPathPasta {

    @Override
    protected String definirPastaRoot() {
        return "videos";
    }

}
