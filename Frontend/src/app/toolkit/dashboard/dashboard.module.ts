import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DashboardComponent } from './dashboard.component';
import { ChartModule } from 'primeng/chart';
import { MenuModule } from 'primeng/menu';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { StyleClassModule } from 'primeng/styleclass';
import { PanelMenuModule } from 'primeng/panelmenu';
import { DashboardsRoutingModule } from './dashboard-routing.module';
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { CardComponent } from 'src/app/layout/card/card.component';
import { HoverableCardComponent } from 'src/app/layout/hoverable-card/hoverable-card.component';
import { HovercardComponent } from 'src/app/layout/hovercard/hovercard.component';
import { TagModule } from 'primeng/tag';
import { AppLayoutModule } from 'src/app/layout/app.layout.module';
import { CanvasJSAngularChartsModule } from '@canvasjs/angular-charts';
import { MergeChartComponent } from 'src/app/layout/merge-chart/merge-chart.component';
import { IssueChartComponent } from 'src/app/layout/issue-chart/issue-chart.component';
@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ChartModule,
        MenuModule,
        TableModule,
        StyleClassModule,
        PanelMenuModule,
        ButtonModule,
        DashboardsRoutingModule,
        OverlayPanelModule,
        TagModule,
        AppLayoutModule,
        CanvasJSAngularChartsModule  
    ],
    declarations: [
        DashboardComponent,
        CardComponent,
        HoverableCardComponent,
        HovercardComponent,
        MergeChartComponent,
        IssueChartComponent
    ],
})
export class DashboardModule {}
