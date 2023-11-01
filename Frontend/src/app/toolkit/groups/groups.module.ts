import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GroupsComponent } from './groups.component';
import { GroupsRoutingModule } from './groups-routing.module';
import { AppLayoutModule } from 'src/app/layout/app.layout.module';
import { AccordionModule } from 'primeng/accordion';
import { DropdownModule } from 'primeng/dropdown';
import { FormsModule } from '@angular/forms';
import { TabMenuModule } from 'primeng/tabmenu';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { PaginatorModule } from 'primeng/paginator';

@NgModule({
    declarations: [GroupsComponent],
    imports: [
        CommonModule,
        GroupsRoutingModule,
        AppLayoutModule,
        AccordionModule,
        DropdownModule,
        FormsModule,
        TabMenuModule,
        ButtonModule,
        CalendarModule,
        PaginatorModule,
    ],
})
export class GroupsModule {}
