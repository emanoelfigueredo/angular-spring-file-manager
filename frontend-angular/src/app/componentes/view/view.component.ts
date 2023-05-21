import { EmissorCriacaoDiretorio } from 'src/app/services/emitir-criacao-diretorio';
import { Tema } from './../../../model/tema';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements EmissorCriacaoDiretorio {

  @Input()
  tema: Tema = Tema.ESCURO;

  @Output()
  eventoAbrirModalAdicionarPasta = new EventEmitter();

  public abrirModalAdiconarPasta(): void {
    this.eventoAbrirModalAdicionarPasta.emit();
  }

}
