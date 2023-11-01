import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BranchListComponent } from './branch-list/branch-list.component';
import { DropdownModule } from 'primeng/dropdown';
import { CommitRoutingModule } from './commit-routing.module';
import { FormsModule } from '@angular/forms';
import { CommitComponent } from './commit.component';
import { InputTextModule } from 'primeng/inputtext';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { PaginatorModule } from 'primeng/paginator';
import { PanelModule } from 'primeng/panel';
import { AppLayoutModule } from 'src/app/layout/app.layout.module';

@NgModule({
    declarations: [BranchListComponent, CommitComponent],
    imports: [
        CommonModule,
        CommitRoutingModule,
        DropdownModule,
        FormsModule,
        InputTextModule,
        CardModule,
        ButtonModule,
        PaginatorModule,
        PanelModule,
        AppLayoutModule,
    ],
})
export class CommitModule {}
