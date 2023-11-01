import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-avatar',
  templateUrl: './avatar.component.html',
  styleUrls: ['./avatar.component.css']
})

export class AvatarComponent implements OnInit {
  @Input() avatarUrl: string | undefined;
  @Input() name: string = '';
  @Input() username: string = '';
  firstLetter(name: string): string {
    return name.charAt(0).toUpperCase();
  }
  
  ngOnInit(): void {
      console.log(this.name);
      console.log(this.username);
      console.log(this.firstLetter)
      
  }
}
