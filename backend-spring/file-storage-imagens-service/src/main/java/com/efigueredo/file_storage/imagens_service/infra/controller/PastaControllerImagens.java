package com.efigueredo.file_storage.imagens_service.infra.controller;

import com.efigueredo.file_storage.shared.infra.controller.PastaController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("imagens/pastas")
@CrossOrigin("http://localhost:4200")
public class PastaControllerImagens  extends PastaController {

}
