import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { FileUtil } from 'src/app/services/manipulador-files';
import { PreviewFile } from '../preview-file';

@Component({
  selector: 'app-preview-audio',
  templateUrl: './preview-audio.component.html',
  styleUrls: ['./preview-audio.component.css']
})
export class PreviewAudioComponent extends PreviewFile implements OnInit {

  @Input()
  file!: File;

  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    const extencaoArquivo = FileUtil.obterExtecaoArquivo(this.file);
    this.uploadForm = this.fb.group({
      file: [this.file],
      fileName: [FileUtil.obterNomeArquivo(this.file)],
      fileNameExtencao: [extencaoArquivo]
    });
    this.uploadForm.get('fileNameExtencao')?.disable();
    this.obterArquivo();
  }

  protected obterArquivo(): void {
    if(this.file) {
      super.srcFile = URL.createObjectURL(this.file);
      this.tamanhoArquivo = FileUtil.obterTamanhoArquivo(this.file.size);
    }
  }

}
