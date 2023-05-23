import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Modal } from 'src/app/model/modal';
import { FileUtil } from 'src/app/services/manipulador-files';

@Component({
  selector: 'app-modal-adicionar-arquivo',
  templateUrl: './modal-adicionar-arquivo.component.html',
  styleUrls: ['./modal-adicionar-arquivo.component.css']
})
export class ModalAdicionarArquivoComponent extends Modal {

  @Input()
  modalAdicionarArquivosAberto: boolean = true;

  @Output()
  modalFechado = new EventEmitter();

  dropFileAllScreen: boolean = false;
  tamanhoArquivo!: string;
  files: File[] = [];

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
    this.modalAdicionarArquivosAberto = false;
    this.modalFechado.emit();
  }

}
