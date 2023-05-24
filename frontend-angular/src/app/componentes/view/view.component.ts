import { EmissorCriacaoDiretorio } from 'src/app/services/emitir-criacao-diretorio';
import { Tema } from './../../../model/tema';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { TipoArquivo } from 'src/app/model/arquivo/tipo-arquivo';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements EmissorCriacaoDiretorio {

  @Input()
  tipoDeArquivos!: TipoArquivo;

  @Input()
  tema: Tema = Tema.ESCURO;

  @Output()
  eventoAbrirModalAdicionarPasta = new EventEmitter();

  @Output()
  eventoAbrirModalAdicionarArquivo = new EventEmitter();

  public abrirModalAdiconarPasta(): void {
    this.eventoAbrirModalAdicionarPasta.emit();
  }

  public abrirModalAdicionarArquivo(): void {
    this.eventoAbrirModalAdicionarArquivo.emit();
  }

}
