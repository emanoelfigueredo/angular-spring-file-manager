import { Component, OnInit } from '@angular/core';
import { Tema } from 'src/model/tema';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'dashboard-file-manager';
  tema: Tema = Tema.ESCURO;

  ngOnInit(): void {

  }

  public trocarTemaAtual(tema: Tema): void {
    this.tema = tema;
  }

}
