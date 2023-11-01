import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-msteams',
  templateUrl: './msteams.component.html',
  styleUrls: ['./msteams.component.css'],
})
export class MSTeamsComponent {
  showIcons = false;
  // Default email value
  @Input() email: string = 'test@soprabanking.com';
  // Default message value 
  @Input() message: string = 'Test Message'; 
  // Default link text
  @Input() linkText: string = 'Test Link'; 
  // Default link styles
  @Input() linkStyles: { [key: string]: string } = {}; 

  generateLinkChat(): string {
    // Construct the dynamic URL with email and message parameters
    const url = `MSTeams:/l/chat/0/0?users=${this.email}&message=${this.message}`;
    return url;
  }

  generateLinkCall(): string {
    // Construct the dynamic URL with email and message parameters
    const url = `MSTeams:/l/call/0/0?users=${this.email}&message=${this.message}`;
    return url;
  }

  generateLinkVideoCall(): string {
    // Construct the dynamic URL with email and message parameters
    const url = `MSTeams:/l/call/0/0?users=${this.email}&message=${this.message}&withVideo=true`;
    return url;
  }
}
