import { Tema } from './../../../../model/tema';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-paginacao',
  templateUrl: './paginacao.component.html',
  styleUrls: ['./paginacao.component.css']
})
export class PaginacaoComponent {

  @Input()
  tema: Tema = Tema.ESCURO;

}
