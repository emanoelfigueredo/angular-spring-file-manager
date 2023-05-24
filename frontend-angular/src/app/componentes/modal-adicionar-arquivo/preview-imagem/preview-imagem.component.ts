import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { FileUtil } from 'src/app/services/manipulador-files';
import { PreviewFile } from '../preview-file';

@Component({
  selector: 'app-preview-imagem',
  templateUrl: './preview-imagem.component.html',
  styleUrls: ['./preview-imagem.component.css']
})
export class PreviewImagemComponent extends PreviewFile implements OnInit {

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

  public obterArquivo(): void {
    if(this.file) {
      super.obterUrlArquivo(this.file);
      this.tamanhoArquivo = FileUtil.obterTamanhoArquivo(this.file.size);
    }
  }

}
