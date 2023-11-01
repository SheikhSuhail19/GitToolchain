import { Component, ElementRef, ViewChild } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { LayoutService } from "../app.layout.service";
import { ProfileService } from 'src/app/services/profile.service';

@Component({
    selector: 'app-topbar',
    templateUrl: './app.topbar.component.html'
})
export class AppTopBarComponent {

    items!: MenuItem[];

    @ViewChild('menubutton') menuButton!: ElementRef;

    @ViewChild('topbarmenubutton') topbarMenuButton!: ElementRef;

    @ViewChild('topbarmenu') menu!: ElementRef;

    profileData={};
    constructor(public layoutService: LayoutService, private profileService:ProfileService) { }
    getProfileData(){
        this.profileService.getData().subscribe((data)=>{
            this.profileData = data[0]; 
            console.log(this.profileData)
        });
       }
    ngOnInit(){
        this.getProfileData();
    }}
