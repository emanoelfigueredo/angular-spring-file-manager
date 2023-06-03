package com.efigueredo.file_storage.shared.infra.controller;

import com.efigueredo.file_storage.shared.domain.PastaRepository;
import com.efigueredo.file_storage.shared.service.ConvertedorStringJsonParaDto;
import com.efigueredo.file_storage.shared.service.files.FileStorageDiscoService;
import com.efigueredo.file_storage.shared.service.files.VerificadorService;
import com.efigueredo.file_storage.shared.service.pastas.PastaDiscoService;
import org.springframework.beans.factory.annotation.Autowired;

public class FileStorageController {

    @Autowired
    protected VerificadorService verificadorVideos;

    @Autowired
    protected ConvertedorStringJsonParaDto convertedorStringJsonParaDto;

    @Autowired
    protected PastaRepository pastaRepository;

    @Autowired
    protected FileStorageDiscoService videosDiscoService;

    @Autowired
    protected PastaDiscoService pastaDiscoService;

}
