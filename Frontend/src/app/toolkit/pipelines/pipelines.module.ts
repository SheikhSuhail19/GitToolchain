import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PipelinesComponent } from './pipelines.component';
import { PipelinesRoutingModule } from './pipelines-routing.module';
import { TableModule } from 'primeng/table';
import { TagModule } from 'primeng/tag';
import { TabMenuComponent } from 'src/app/layout/tab-menu/tab-menu.component';
import { AppLayoutModule } from 'src/app/layout/app.layout.module';

@NgModule({
    declarations: [PipelinesComponent],
    imports: [
        CommonModule,
        PipelinesRoutingModule,
        TableModule,
        TagModule,
        AppLayoutModule,
    ],
})
export class PipelinesModule {}
