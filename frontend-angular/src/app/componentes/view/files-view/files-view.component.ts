import { TipoArquivo } from 'src/app/model/arquivo/tipo-arquivo';
import { Tema } from './../../../../model/tema';
import { faAdd, faSearch, faTrash } from '@fortawesome/free-solid-svg-icons';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-files-view',
  templateUrl: './files-view.component.html',
  styleUrls: ['./files-view.component.css']
})
export class FilesViewComponent {

  @Input()
  tema: Tema = Tema.ESCURO;

  @Input()
  tipoArquivos!: TipoArquivo;

  @Output()
  eventoAbrirModalAdicionarArquivo = new EventEmitter();

  files!: any[];

  readonly iconeSearch = faSearch;
  readonly iconeDeletar = faTrash;
  readonly iconeAdicionar = faAdd;

  public abrirModalAdicionarArquivo(): void {
    this.eventoAbrirModalAdicionarArquivo.emit();
  }

}
