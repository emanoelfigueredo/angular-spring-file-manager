import { Injectable } from '@angular/core';
import { TipoArquivo } from '../model/arquivo/tipo-arquivo';

@Injectable({
  providedIn: 'root'
})
export class ResolvedorExtencoesService {

  private mapaTipoArquivos = new Map();

  constructor() {
    this.mapaTipoArquivos.set(TipoArquivo.ARQUIVO, ".docx, .txt, .doc, .pdf, .xlsx, .xls, .pptx, .ppt, .rar, .zip, .7z," +
    ".exe, .html, .css, .js, .java, .py, .php, .aspx, .sql, .xml, .json, .psd, .cdr, .jsp, .cfm, .fla, .swf, .dwg, .htm," +
    ".odt, .ods, .ts, .yml, .properties, .c");
    this.mapaTipoArquivos.set(TipoArquivo.IMAGEM, ".gif, .png, .jpeg, .jpg, .ico");
    this.mapaTipoArquivos.set(TipoArquivo.AUDIO, ".3gp, .mp3, .aac, .wma, .ac3, .wav, .ogg");
    this.mapaTipoArquivos.set(TipoArquivo.VIDEO, ".mp4, .mov, .mpeg, .rmvb, .mkv");
  }

  public obterExtencoesPermitidas(tipo: TipoArquivo): string {
    return this.mapaTipoArquivos.get(tipo);
  }

}
