package com.efigueredo.file_storage.video_service.infra.controller;

import com.efigueredo.file_storage.shared.infra.controller.PastaController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("videos/pastas")
@CrossOrigin("http://localhost:4200")
public class PastaControllerVideos extends PastaController {

}
