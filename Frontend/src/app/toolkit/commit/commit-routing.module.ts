import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommitComponent } from './commit.component';
import { BranchListComponent } from './branch-list/branch-list.component';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: '',
        component:CommitComponent
      },
      {
        path : 'commits/:branchName',
        component:CommitComponent
      }
      // Add more routes here if needed
    ]),
  ],
  exports: [RouterModule],
})
export class CommitRoutingModule {}
