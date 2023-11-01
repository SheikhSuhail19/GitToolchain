import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-dynamic-hover-icons',
  templateUrl: './dynamic-hover-icons.component.html',
  styleUrls: ['./dynamic-hover-icons.component.css']
})
export class DynamicHoverIconsComponent {
  // Default link text
  @Input() toolTipText: string = 'Test'; 
  // Default link name
  @Input() toolTipName: string = 'Test'; 
  // Default icon class 
  @Input() iconClass: string='Test';  
  // Default function to be called on button 
  @Input() hrefValue:  string="Test"; 
}
