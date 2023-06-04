package com.efigueredo.file_storage.audios_service.infra.controller;

import com.efigueredo.file_storage.shared.infra.controller.PastaController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("audios/pastas")
@CrossOrigin("http://localhost:4200")
public class PastaControllerAudios extends PastaController {

}
