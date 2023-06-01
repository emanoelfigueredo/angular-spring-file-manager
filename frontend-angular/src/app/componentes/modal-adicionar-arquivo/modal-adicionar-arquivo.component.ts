import { TipoArquivo } from 'src/app/model/arquivo/tipo-arquivo';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Modal } from 'src/app/model/modal';
import { ResolvedorExtencoesService } from 'src/app/services/resolvedor-extencoes.service';
import { HttpClient, HttpRequest } from '@angular/common/http'
import { InformacoesUploadVideo } from './upload-video';

@Component({
  selector: 'app-modal-adicionar-arquivo',
  templateUrl: './modal-adicionar-arquivo.component.html',
  styleUrls: ['./modal-adicionar-arquivo.component.css']
})
export class ModalAdicionarArquivoComponent extends Modal {

  @Input()
  modalAdicionarArquivosAberto: boolean = true;

  @Input()
  tipoArquivos!: TipoArquivo;

  @Output()
  modalFechado = new EventEmitter();

  dropFileAllScreen: boolean = false;
  tamanhoArquivo!: string;
  files: File[] = [];

  constructor(private readonly resolvedorExtencoesArquivos: ResolvedorExtencoesService, private readonly httpClient: HttpClient) {
    super();
  }

  public ativarDropAllScreen() {
    this.dropFileAllScreen = true;
  }

  public desativarDropAllScreen() {
    this.dropFileAllScreen = false;
  }

  public adicionarArquivos(event: any): void {
    this.files.push(...event.target.files);
    console.log(this.files)
  }

  public adicionarArquivosDrop(event: any): void {
    console.log(event)
    this.files.push(...event.addedFiles);
  }

  public removerArquivo(file: File) {
    this.files.splice(this.files.indexOf(file), 1);
  }

  public obterMargemBottomQuandoTiverArquivo(): string {
    if(this.files.length > 0) {
      return 'margin-bottom-1em';
    }
    return '';
  }

  public fecharModal(): void {
    this.files = [];
    this.modalAdicionarArquivosAberto = false;
    this.modalFechado.emit();
  }

  public obterTiposDeArquivosQueSeraoAceitos(): string {
    return this.resolvedorExtencoesArquivos.obterExtencoesPermitidas(this.tipoArquivos);
  }

  public obterPosicaoExcluirFile(): string {
    if(this.tipoArquivos == TipoArquivo.IMAGEM) {
      return 'top-1em';
    }
    if(this.tipoArquivos == TipoArquivo.VIDEO) {
      return 'top-1-5em';
    }
    return '';
  }

  public realizarUpload(): void {

    const data: FormData = new FormData();

    this.files.forEach(file => {
      console.log(file)
      data.append("videos", file)

      let d: InformacoesUploadVideo = {
        nome: file.name,
        extencao: "mp4",
        tamanho: file.size,
        idPasta: "6474e55231d9207f3ef763ed"
      }

      data.append("dados", JSON.stringify(d))
    });


    const newRequest = new HttpRequest('POST', 'http://localhost:8081/videos', data, {
      reportProgress: true
    });
    this.httpClient.request(newRequest).subscribe((response) => {
      console.log(response);
    });
  }

}
