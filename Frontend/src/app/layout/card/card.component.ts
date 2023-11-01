import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent {
  @Input() cardData: any;
  @Input() classData:any;
  check:boolean = true;
  constructor(){}
  ngOnInit(){}
  
}
