import { Component, Input, OnInit } from '@angular/core';
import { ProfileService } from 'src/app/services/profile.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
})
export class ProfileComponent implements OnInit {
    @Input() profileData:any;
    isAvatar:boolean=false;
    
        constructor(
           private profileService: ProfileService, private router: Router  
        ) {
        }

      
        ngOnInit() {
            if(this.profileData.avatar_url != null){
                this.isAvatar = true;
            }
        }

    getValue(changedValue){
        console.log(changedValue);

    }
    logout() {
        sessionStorage.removeItem('accessToken');
        this.router.navigate(['/auth/login']);
    }
}