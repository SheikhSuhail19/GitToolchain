import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CardModule } from 'primeng/card';
import { LabelComponent } from './layout/label/label.component';
import { ButtonModule } from 'primeng/button';
import { BadgeModule } from 'primeng/badge';
import { MenuModule } from 'primeng/menu';
import { ToastModule } from 'primeng/toast';
import { HttpClientModule } from '@angular/common/http';
import { AccordionModule } from 'primeng/accordion';
import { RadioButtonModule } from 'primeng/radiobutton';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { TabMenuModule } from 'primeng/tabmenu';
import { CalendarModule } from 'primeng/calendar';
import { FormsModule } from '@angular/forms';
import { PaginatorModule } from 'primeng/paginator';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { AppLayoutModule } from './layout/app.layout.module';
import { NotfoundComponent } from './toolkit/notfound/notfound.component';
import { CommonModule } from '@angular/common';
import { MembersComponent } from './toolkit/members/members.component';
import { AvatarComponent } from './layout/avatar/avatar.component';
import { CommitModule } from './toolkit/commit/commit.module';
import { TranslocoRootModule } from './transloco-root.module';

@NgModule({
    declarations: [AppComponent, NotfoundComponent],
    imports: [
        BrowserModule,
        AppRoutingModule,
        AppLayoutModule,
        CommonModule,
        BrowserAnimationsModule,
        CardModule,
        ButtonModule,
        BadgeModule,
        MenuModule,
        ToastModule,
        HttpClientModule,
        AccordionModule,
        RadioButtonModule,
        DropdownModule,
        InputTextModule,
        TabMenuModule,
        CalendarModule,
        FormsModule,
        PaginatorModule,
        CommitModule,
        TranslocoRootModule,
    ],
    providers: [{ provide: LocationStrategy, useClass: HashLocationStrategy }],
    bootstrap: [AppComponent],
})
export class AppModule {}
