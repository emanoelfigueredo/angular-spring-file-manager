import { Output } from '@angular/core';
import { Component, EventEmitter, Input } from '@angular/core';
import { faAdd } from '@fortawesome/free-solid-svg-icons';
import { Subject } from 'rxjs';
import { EmissorCriacaoDiretorio } from 'src/app/services/emitir-criacao-diretorio';
import { Tema } from 'src/model/tema';

@Component({
  selector: 'app-diretorios-card',
  templateUrl: './diretorios-card.component.html',
  styleUrls: ['./diretorios-card.component.css']
})
export class DiretoriosCardComponent implements EmissorCriacaoDiretorio {

  readonly iconeAdicionar = faAdd;

  @Input()
  tema: Tema = Tema.ESCURO;

  @Output()
  eventoAbrirModalAdicionarPasta = new EventEmitter();

  public abrirModalAdiconarPasta(): void {
    this.eventoAbrirModalAdicionarPasta.emit();
  }

}
