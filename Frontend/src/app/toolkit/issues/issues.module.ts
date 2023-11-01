import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IssuesComponent } from './issues.component';
import { AccordionModule } from 'primeng/accordion';
import { DropdownModule } from 'primeng/dropdown';
import { FormsModule } from '@angular/forms';
import { TabMenuModule } from 'primeng/tabmenu';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { EntityCardComponent } from '../../layout/entity-card/entity-card.component';
import { PaginatorModule } from 'primeng/paginator';
import { LabelComponent } from '../../layout/label/label.component';
import { RepositoryDropdownComponent } from 'src/app/layout/repository-dropdown/repository-dropdown.component';
import { AppLayoutModule } from 'src/app/layout/app.layout.module';
import { IssuesRoutingModule } from './issues-routing.module';
import {MultiSelectModule} from 'primeng/multiselect';
import { TabMenuComponent } from 'src/app/layout/tab-menu/tab-menu.component';




@NgModule({
  declarations: [
    IssuesComponent 
  ],
  imports: [
    CommonModule,
    IssuesRoutingModule,
    AccordionModule,
    AppLayoutModule,
    DropdownModule,
    FormsModule,
    MultiSelectModule,
    TabMenuModule,
    ButtonModule,
    CalendarModule,
    PaginatorModule,
  ]
})
export class IssuesModule { }
