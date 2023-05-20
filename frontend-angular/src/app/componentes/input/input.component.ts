import { Component, Input } from '@angular/core';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import { Tema } from 'src/model/tema';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.css']
})
export class InputComponent {


  readonly iconePesquisa = faSearch;

  @Input()
  tema: Tema = Tema.ESCURO;

}
