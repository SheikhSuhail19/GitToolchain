import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from '../app.layout.service';

import { TranslocoModule } from '@ngneat/transloco';
import { TranslocoService } from '@ngneat/transloco';

@Component({
    selector: 'app-menu',
    templateUrl: './app.menu.component.html',
})
export class AppMenuComponent implements OnInit {
    model: any[] = [];
    sample: any[] = [];

    constructor(private translateService: TranslocoService) { }
    label1: string = '';
    language = {};

    
        ngOnInit() {
        this.translateService.langChanges$.subscribe(lang => {
            this.translateService.load(`${lang}`).subscribe(
                (value) => {
                        this.makeModel(value);
                }
            );
        })
    }


    makeModel( value:any){
        this.model = [
            {
                label: value['home'].main,
                items: [
                    {
                        label: value['home'].dashboard,
                        icon: 'pi pi-fw pi-home',
                        routerLink: ['/home/dashboard'],
                    },
                ],
            },
            {
                label: value['your_work'].main,
                items: [
                    {
                        label: value['your_work'].groups,
                        icon: 'pi pi-fw pi-id-card',
                        routerLink: ['/home/groups'],
                    },
                    {
                        label: value['your_work'].commits,
                        icon: 'pi pi-fw pi-box',
                        routerLink: ['/home/commits'],
                    },
                    {
                        label: value['your_work'].repository,
                        icon: 'pi pi-fw pi-table',
                        routerLink: ['/home/repository'],
                    },
                    {
                        label: value['your_work'].branches,
                        icon: 'pi pi-fw pi-list',
                        routerLink: ['/home/branches'],
                    },
                    {
                        label: value['your_work'].merge_req,
                        icon: 'pi pi-fw pi-share-alt',
                        routerLink: ['/home/merge'],
                    },
                    {
                        label: 'Issues',
                        icon: 'pi pi-fw pi-exclamation-circle',
                        routerLink: ['/home/issues'],
                    },
                    {
                        label: value['your_work'].members,
                        icon: 'pi pi-fw pi-users',
                        routerLink: ['/home/members'],
                    },
                    {
                        label: value['your_work'].pipelines,
                        icon: 'pi pi-fw pi-sliders-v',
                        routerLink: ['/home/pipelines'],
                    },
                    {
                        label: value['your_work'].charts,
                        icon: 'pi pi-fw pi-chart-bar',
                        routerLink: ['/someModule/charts'],
                    },
                    {
                        label: value['your_work'].misc,
                        icon: 'pi pi-fw pi-circle',
                        routerLink: ['/someModule/misc'],
                    },
                ],
            },
    
            {
                label: value['others'].main,
                icon: 'pi pi-fw pi-briefcase',
                items: [
                    {
                        label: value['others'].auth,
                        icon: 'pi pi-fw pi-user',
                        items: [
                            {
                                label: value['others'].login,
                                icon: 'pi pi-fw pi-sign-in',
                                routerLink: ['/auth/login'],
                            },
                            {
                                label: value['others'].error,
                                icon: 'pi pi-fw pi-times-circle',
                                routerLink: ['/auth/error'],
                            },
                            {
                                label: value['others'].access_denied,
                                icon: 'pi pi-fw pi-lock',
                                routerLink: ['/auth/access'],
                            },
                        ],
                    },
                    {
                        label: 'Web',
                        icon: 'pi pi-fw pi-globe',
                        url: ['https://www.google.com'],
                        target: '_blank',
                    },
                ],
            },
        ];
    }
 
}
