import { Component, Input, OnInit } from '@angular/core';
import { Tema } from 'src/model/tema';
import { EmissorCriacaoDiretorio } from './services/emitir-criacao-diretorio';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'dashboard-file-manager';
  tema: Tema = Tema.ESCURO;

  modalCriarPastaAberto: boolean = false;

  ngOnInit(): void {

  }

  public abrirModalCriarDiretorio(): void {
    this.modalCriarPastaAberto = true;
  }

  public fecharModalCriarDiretorio(): void {
    this.modalCriarPastaAberto = false;
  }

  public trocarTemaAtual(tema: Tema): void {
    this.tema = tema;
  }

}
