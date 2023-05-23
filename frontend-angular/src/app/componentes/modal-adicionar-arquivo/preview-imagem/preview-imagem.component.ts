import { ThisReceiver } from '@angular/compiler';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { FileUtil } from 'src/app/services/manipulador-files';

@Component({
  selector: 'app-preview-imagem',
  templateUrl: './preview-imagem.component.html',
  styleUrls: ['./preview-imagem.component.css']
})
export class PreviewImagemComponent implements OnInit {

  @Input()
  file!: File;

  uploadForm!: any;
  imagemURL!: any;
  tamanhoArquivo!: any;

  constructor(private fb: FormBuilder) {}

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
      if(FileUtil.verificarSeArquivoEnviadoEImagem(this.file)) {
        this.inserirAtributosImagemNoModal(this.file);
      } else {
        this.inserirAtributosArquivoNoModal(this.file);
      }
      this.tamanhoArquivo = this.obterTamanhoArquivo(this.file.size);
    }
  }

  private inserirAtributosArquivoNoModal(file: File): void {

  }

  private obterTamanhoArquivo(bytes: number): string {
    const decimals = 3;
    if (bytes === 0) return '0 Bytes';
    const k = 1024;
    const dm = decimals < 0 ? 0 : decimals;
    const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
    const i = Math.floor(Math.log(bytes) / Math.log(k));
    return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
}

  private inserirAtributosImagemNoModal(file: File): void {
    this.inserirImagemPreview(file);
  }

  private inserirImagemPreview(file: File): void {
      this.uploadForm.patchValue({
        file: file
      });
      this.uploadForm.get('file')?.updateValueAndValidity();
      const reader = new FileReader();
      reader.onload = () => {
        this.imagemURL = reader.result as string;
      }
      this.file
      reader.readAsDataURL(file);
  }

}
