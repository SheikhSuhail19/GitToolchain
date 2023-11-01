import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { IssuesComponent } from './issues.component';

@NgModule({
    imports: [
        RouterModule.forChild([{ path: '', component: IssuesComponent }]),
    ],
    exports: [RouterModule],
})
export class IssuesRoutingModule {}
