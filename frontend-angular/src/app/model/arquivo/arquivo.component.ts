import { Component } from '@angular/core';
import { faEdit, faStar } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-arquivo',
  templateUrl: './arquivo.component.html',
  styleUrls: ['./arquivo.component.css']
})
export class ArquivoComponent {

  readonly iconeEditar = faEdit;
  readonly iconeFavoritar = faStar;

}
