import { Tema } from './../../../../model/tema';
import { faSearch, faTrash } from '@fortawesome/free-solid-svg-icons';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-files-view',
  templateUrl: './files-view.component.html',
  styleUrls: ['./files-view.component.css']
})
export class FilesViewComponent {

  @Input()
  tema: Tema = Tema.ESCURO;

  readonly iconeSearch = faSearch;
  readonly iconeDeletar = faTrash;

}
