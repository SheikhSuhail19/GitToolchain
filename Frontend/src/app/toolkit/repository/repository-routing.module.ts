import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { RepositoryComponent } from './repository.component';

@NgModule({
    imports: [
        RouterModule.forChild([{ path: '', component: RepositoryComponent }]),
    ],
    exports: [RouterModule],
})
export class RepositoryRoutingModule {}
