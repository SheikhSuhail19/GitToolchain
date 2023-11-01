import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MergeComponent } from './merge.component';
import { MergeRoutingModule } from './merge-routing.module';
import { FormsModule } from '@angular/forms';
import { CardModule } from 'primeng/card';
import { TabMenuModule } from 'primeng/tabmenu';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { BadgeModule } from 'primeng/badge';
import { PaginatorModule } from 'primeng/paginator';
import { AppLayoutModule } from 'src/app/layout/app.layout.module';
import { TooltipModule } from 'primeng/tooltip';
import { DialogModule} from 'primeng/dialog';
import { RadioButtonModule } from 'primeng/radiobutton';
import { ToastModule } from 'primeng/toast';

@NgModule({
    declarations: [MergeComponent],
    imports: [
        CommonModule,
        MergeRoutingModule,
        CardModule,
        FormsModule,
        TabMenuModule,
        InputTextModule,
        ButtonModule,
        BadgeModule,
        PaginatorModule,
        AppLayoutModule,
        TooltipModule,
        RadioButtonModule,
        DialogModule,
        ToastModule,
       
    ],
})
export class MergeModule {}
