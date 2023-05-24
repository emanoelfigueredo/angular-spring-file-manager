import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { FileUtil } from 'src/app/services/manipulador-files';
import { PreviewFile } from '../preview-file';

@Component({
  selector: 'app-preview-video',
  templateUrl: './preview-video.component.html',
  styleUrls: ['./preview-video.component.css']
})
export class PreviewVideoComponent extends PreviewFile implements OnInit {

  @Input()
  file!: File;

  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.uploadForm = this.fb.group({
      file: [this.file],
      fileName: [FileUtil.obterNomeArquivo(this.file)],
      fileNameExtencao: [FileUtil.obterExtecaoArquivo(this.file)]
    });
    this.uploadForm.get('fileNameExtencao')?.disable();
    this.obterArquivo();
  }

  protected obterArquivo(): void {
    if(this.file) {
      super.obterUrlArquivo(this.file);
      this.tamanhoArquivo = FileUtil.obterTamanhoArquivo(this.file.size);
    }
  }

}
