import { Component, Input } from '@angular/core';
import { faAdd } from '@fortawesome/free-solid-svg-icons';
import { Tema } from 'src/model/tema';

@Component({
  selector: 'app-diretorios-card',
  templateUrl: './diretorios-card.component.html',
  styleUrls: ['./diretorios-card.component.css']
})
export class DiretoriosCardComponent {

  readonly iconeAdicionar = faAdd;

  @Input()
  tema: Tema = Tema.ESCURO;

}
