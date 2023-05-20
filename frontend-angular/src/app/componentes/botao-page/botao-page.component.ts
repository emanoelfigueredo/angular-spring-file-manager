import { Tema } from './../../../model/tema';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-botao-page',
  templateUrl: './botao-page.component.html',
  styleUrls: ['./botao-page.component.css']
})
export class BotaoPageComponent {

  @Input()
  valor!: string;

  @Input()
  tema: Tema = Tema.ESCURO;

}
