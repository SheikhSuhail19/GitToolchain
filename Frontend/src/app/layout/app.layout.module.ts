import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { InputTextModule } from 'primeng/inputtext';
import { SidebarModule } from 'primeng/sidebar';
import { BadgeModule } from 'primeng/badge';
import { RadioButtonModule } from 'primeng/radiobutton';
import { InputSwitchModule } from 'primeng/inputswitch';
import { RippleModule } from 'primeng/ripple';
import { AppMenuComponent } from './menu/app.menu.component';
import { AppMenuitemComponent } from './service/app.menuitem.component';
import { RouterModule } from '@angular/router';
import { AppTopBarComponent } from './topbar/app.topbar.component';
import { AppFooterComponent } from './footer/app.footer.component';
import { AppSidebarComponent } from './sidebar/app.sidebar.component';
import { AppLayoutComponent } from './app.layout.component';
import { PaginatorModule } from 'primeng/paginator';
import { AccordionModule } from 'primeng/accordion';
import { CalendarModule } from 'primeng/calendar';
import { CommonModule } from '@angular/common';
import { TabMenuModule } from 'primeng/tabmenu';
import { FiltersAccordianComponent } from './filters-accordian/filters-accordian.component';
import { EntityCardComponent } from './entity-card/entity-card.component';
import { PaginationComponent } from './pagination/pagination.component';
import { RepositoryDropdownComponent } from './repository-dropdown/repository-dropdown.component';
import { TabMenuComponent } from './tab-menu/tab-menu.component';
import { PreloaderComponent } from './preloader/preloader.component';
import { DynamicHoverIconsComponent } from './msteams/dynamic-hover-icons/dynamic-hover-icons.component';
import { MSTeamsComponent } from './msteams/msteams.component';
import { CanvasJSAngularChartsModule } from '@canvasjs/angular-charts';
import { DropdownModule } from 'primeng/dropdown';
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { ButtonModule } from 'primeng/button';
import { InternationalizationComponent } from './internationalization/internationalization.component';
import { ProfileComponent } from './profile/profile.component';
import { AvatarModule } from 'primeng/avatar';
import { AvatarGroupModule } from 'primeng/avatargroup';

import { NotificationModule } from '../toolkit/notification/notification.module';

@NgModule({
    declarations: [
        AppMenuitemComponent,
        AppTopBarComponent,
        AppFooterComponent,
        AppMenuComponent,
        AppSidebarComponent,
        AppLayoutComponent,
        FiltersAccordianComponent,
        EntityCardComponent,
        PaginationComponent,
        TabMenuComponent,
        RepositoryDropdownComponent,
        PreloaderComponent,
        DynamicHoverIconsComponent,
        MSTeamsComponent,
        InternationalizationComponent,
       
        
        ProfileComponent,
    ],
    imports: [
        CommonModule,
        // BrowserModule,
        FormsModule,
        HttpClientModule,
        // BrowserAnimationsModule,
        InputTextModule,
        SidebarModule,
        BadgeModule,
        RadioButtonModule,
        InputSwitchModule,
        RippleModule,
        RouterModule,
        PaginatorModule,
        AccordionModule,
        CalendarModule,
        TabMenuModule,
        ButtonModule,
        OverlayPanelModule,
        AvatarGroupModule,
        AvatarModule,
        NotificationModule,
        CanvasJSAngularChartsModule
    ],
    exports: [
        AppLayoutComponent,
        FiltersAccordianComponent,
        EntityCardComponent,
        PaginationComponent,
        TabMenuComponent,
        RepositoryDropdownComponent,
        PreloaderComponent,
        MSTeamsComponent,
   
    ],
})
export class AppLayoutModule {}
