import { Component, Input } from '@angular/core';
import { faGithub, faGoogle, faLinkedin } from '@fortawesome/free-brands-svg-icons';
import { faPhone } from '@fortawesome/free-solid-svg-icons';
import { Tema } from 'src/model/tema';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {

  @Input()
  tema: Tema = Tema.ESCURO;

  readonly iconeGituhb = faGithub;
  readonly iconeLinkedin = faLinkedin;
  readonly iconeGmail = faGoogle;
  readonly iconeTelefone = faPhone;

}
