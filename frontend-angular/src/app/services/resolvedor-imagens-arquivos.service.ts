import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ResolvedorImagensArquivosService {

  private mapaImagensPorExtencao = new Map();
  private PATH: string = "../../../../assets/images/files-types";
  private PATH_FILE_GENERICO = `${this.PATH}/file.png`

  constructor() {
    this.mapaImagensPorExtencao.set(".docx", `${this.PATH}/docx.png`)
    this.mapaImagensPorExtencao.set(".json", `${this.PATH}/json.png`)
    this.mapaImagensPorExtencao.set(".pdf", `${this.PATH}/pdf.png`)
    this.mapaImagensPorExtencao.set(".txt", `${this.PATH}/txt.png`)
    this.mapaImagensPorExtencao.set(".xlsx", `${this.PATH}/xlsx.png`)
    this.mapaImagensPorExtencao.set(".xml", `${this.PATH}/xml.png`)
    this.mapaImagensPorExtencao.set(".zip", `${this.PATH}/zip.png`)
  }

  public obterPathImagemPorExtencao(extencao: string): string {
    let path = this.mapaImagensPorExtencao.get(extencao);
    if(!path) {
      path = this.PATH_FILE_GENERICO;
    }
    return path;
  }

}
