import { TipoArquivo } from './../../model/arquivo/tipo-arquivo';
import { Component, EventEmitter, Output, OnInit } from '@angular/core';
import { faImage, faVideo, faFile, faHeadphones, faSun, faMoon, IconDefinition } from '@fortawesome/free-solid-svg-icons';
import { Tema, trocarTema } from 'src/model/tema';

@Component({
  selector: 'app-barra-lateral',
  templateUrl: './barra-lateral.component.html',
  styleUrls: ['./barra-lateral.component.css']
})
export class BarraLateralComponent implements OnInit {

  readonly iconeFoto = faImage;
  readonly iconeVideo = faVideo;
  readonly iconeArquivos = faFile;
  readonly iconeAudio = faHeadphones;
  readonly iconeSol = faSun;
  readonly iconeLua = faMoon;
  tema: Tema = Tema.ESCURO;
  tipoArquivo: TipoArquivo = TipoArquivo.ARQUIVO;

  @Output()
  alteracaoDeTema = new EventEmitter();

  @Output()
  tipoDeArquivoAlterado = new EventEmitter();

  ngOnInit(): void {
  }

  public obterIconePorTema(): IconDefinition {
    if(this.tema == Tema.CLARO) {
      return this.iconeSol;
    }
    return this.iconeLua;
  }

  public trocarTemaAtual(): void {
    this.tema = trocarTema(this.tema);
    this.emitirEventoDeAlteracaoDeTema();
  }

  private emitirEventoDeAlteracaoDeTema(): void {
    this.alteracaoDeTema.emit(this.tema);
  }

  public trocarTipoArquivo(tipo: string): void {
    this.tipoArquivo = tipo as TipoArquivo
    this.tipoDeArquivoAlterado.emit(tipo);
  }

  public obterCorBotao(tipoArquivo: string): string {
    const tipo = tipoArquivo as TipoArquivo;
    if(this.tipoArquivo == tipo) {
      return 'link-selecionado';
    }
    if(this.tema == Tema.CLARO) {
      return "sections-links__icon-claro";
    } else {
      return "sections-links__icon-escuro";
    }
  }

}
