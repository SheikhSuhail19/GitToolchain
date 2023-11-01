import { Component , Input} from '@angular/core';

@Component({
  selector: 'app-hoverable-card',
  templateUrl: './hoverable-card.component.html',
  styleUrls: ['./hoverable-card.component.css']
})
export class HoverableCardComponent {
  @Input() cardData: any;
  @Input() classData:any;
  hoverCheck: boolean=false;
  constructor(){}
  ngOnInit(){}
}
