import { Component, Input } from '@angular/core';
import { faBell } from '@fortawesome/free-solid-svg-icons';
import { Tema } from 'src/model/tema';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  readonly iconeNotificacao = faBell;

  @Input()
  tema: Tema = Tema.ESCURO;

}
