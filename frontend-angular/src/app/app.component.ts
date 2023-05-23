import { Component } from '@angular/core';
import { Tema } from 'src/model/tema';
import { EmissorCriacaoDiretorio } from './services/emitir-criacao-diretorio';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'dashboard-file-manager';
  tema: Tema = Tema.ESCURO;

  modalCriarPastaAberto: boolean = false;
  modalAdicionarArquivosAberto: boolean = false;

  public trocarTemaAtual(tema: Tema): void {
    this.tema = tema;
  }

}
