import { Component, EventEmitter, Output } from '@angular/core';
import { faImage, faVideo, faFile, faHeadphones, faSun, faMoon, IconDefinition } from '@fortawesome/free-solid-svg-icons';
import { Tema, trocarTema } from 'src/model/tema';

@Component({
  selector: 'app-barra-lateral',
  templateUrl: './barra-lateral.component.html',
  styleUrls: ['./barra-lateral.component.css']
})
export class BarraLateralComponent {

  readonly iconeFoto = faImage;
  readonly iconeVideo = faVideo;
  readonly iconeArquivos = faFile;
  readonly iconeAudio = faHeadphones;
  readonly iconeSol = faSun;
  readonly iconeLua = faMoon;
  tema: Tema = Tema.ESCURO;

  @Output()
  alteracaoDeTema = new EventEmitter();

  public trocarTemaAtual(): void {
    this.tema = trocarTema(this.tema);
    this.emitirEventoDeAlteracaoDeTema();
  }

  public obterIconePorTema(): IconDefinition {
    if(this.tema == Tema.CLARO) {
      return this.iconeSol;
    }
    return this.iconeLua;
  }

  private emitirEventoDeAlteracaoDeTema(): void {
    this.alteracaoDeTema.emit(this.tema);
  }

}
