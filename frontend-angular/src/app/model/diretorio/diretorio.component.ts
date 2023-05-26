import { Component, Input } from '@angular/core';
import { faFolder, faStar } from '@fortawesome/free-solid-svg-icons';
import { Tema } from 'src/model/tema';

@Component({
  selector: 'app-diretorio',
  templateUrl: './diretorio.component.html',
  styleUrls: ['./diretorio.component.css']
})
export class DiretorioComponent {

  readonly iconeDiretorio = faFolder;
  readonly iconeEstrela = faStar;

  @Input()
  tema: Tema = Tema.CLARO;



  files!: any[];

  teste(event: any) {
    const elementoDropado = event.item.element.nativeElement as HTMLElement;
    console.log(elementoDropado.getAttribute("data-id"))
  }

}
