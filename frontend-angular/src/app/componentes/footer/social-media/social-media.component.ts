import { IconDefinition } from '@fortawesome/free-solid-svg-icons';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-social-media',
  templateUrl: './social-media.component.html',
  styleUrls: ['./social-media.component.css']
})
export class SocialMediaComponent {

  @Input()
  link!: string;

  @Input()
  icone!: IconDefinition;

}
