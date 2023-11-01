import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BranchesComponent } from './branches.component';
import { AccordionModule } from 'primeng/accordion';
import { DropdownModule } from 'primeng/dropdown';
import { FormsModule } from '@angular/forms';
import { TabMenuModule } from 'primeng/tabmenu';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { BranchesRoutingModule } from './branches-routing.module';
import { EntityCardComponent } from '../../layout/entity-card/entity-card.component';
import { PaginatorModule } from 'primeng/paginator';
import { LabelComponent } from '../../layout/label/label.component';
import { RepositoryDropdownComponent } from 'src/app/layout/repository-dropdown/repository-dropdown.component';
import { AppLayoutModule } from 'src/app/layout/app.layout.module';

@NgModule({
    declarations: [BranchesComponent, LabelComponent],
    imports: [
        CommonModule,
        BranchesRoutingModule,
        AccordionModule,
        AppLayoutModule,
        DropdownModule,
        FormsModule,
        TabMenuModule,
        ButtonModule,
        CalendarModule,
        PaginatorModule,
    ],
})
export class BranchesModule {}
