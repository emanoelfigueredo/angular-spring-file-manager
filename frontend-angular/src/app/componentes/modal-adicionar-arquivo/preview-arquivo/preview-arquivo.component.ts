import { ResolvedorImagensArquivosService } from './../../../services/resolvedor-imagens-arquivos.service';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { FileUtil } from 'src/app/services/manipulador-files';
import { PreviewFile } from '../preview-file';

@Component({
  selector: 'app-preview-arquivo',
  templateUrl: './preview-arquivo.component.html',
  styleUrls: ['./preview-arquivo.component.css']
})
export class PreviewArquivoComponent extends PreviewFile implements OnInit {

  @Input()
  file!: File;

  imagemPreviewArquivo!: string;

  constructor(
      private fb: FormBuilder,
      private readonly resolvedorImagemArquivos: ResolvedorImagensArquivosService
    ) {
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
    this.imagemPreviewArquivo = this.resolvedorImagemArquivos
                      .obterPathImagemPorExtencao(extencaoArquivo);
  }

  protected obterArquivo(): void {
    if(this.file) {
      super.obterUrlArquivo(this.file);
      this.tamanhoArquivo = FileUtil.obterTamanhoArquivo(this.file.size);
    }
  }

}
