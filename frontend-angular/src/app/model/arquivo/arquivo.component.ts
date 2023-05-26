import { Component, Input } from '@angular/core';
import { faEdit, faStar } from '@fortawesome/free-solid-svg-icons';
import { Tema } from 'src/model/tema';

@Component({
  selector: 'app-arquivo',
  templateUrl: './arquivo.component.html',
  styleUrls: ['./arquivo.component.css']
})
export class ArquivoComponent {

  readonly iconeEditar = faEdit;
  readonly iconeFavoritar = faStar;

  @Input()
  tema: Tema = Tema.ESCURO;

}
