import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MergeComponent } from './merge.component';

@NgModule({
    imports: [
        RouterModule.forChild([{ path: '', component: MergeComponent }]),
    ],
    exports: [RouterModule],
})
export class MergeRoutingModule {}
