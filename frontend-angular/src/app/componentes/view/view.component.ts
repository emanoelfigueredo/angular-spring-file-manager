import { Tema } from './../../../model/tema';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent {

  @Input()
  tema: Tema = Tema.ESCURO;

}
