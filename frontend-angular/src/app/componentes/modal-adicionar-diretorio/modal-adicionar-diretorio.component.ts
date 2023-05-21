import { Component, Input, Output, EventEmitter } from '@angular/core';
import { faClose } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-modal-adicionar-diretorio',
  templateUrl: './modal-adicionar-diretorio.component.html',
  styleUrls: ['./modal-adicionar-diretorio.component.css']
})
export class ModalAdicionarDiretorioComponent {

  readonly iconeSair = faClose;

  @Input()
  modalCriarPastaAberto: boolean = false;

  @Output()
  modalFechado = new EventEmitter();

  public fecharModal(): void {
    this.modalCriarPastaAberto = false;
    this.modalFechado.emit();
  }

}
