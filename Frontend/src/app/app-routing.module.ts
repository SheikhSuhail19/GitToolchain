import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { NotfoundComponent } from './toolkit/notfound/notfound.component';
import { AppLayoutComponent } from './layout/app.layout.component';

@NgModule({
    imports: [
        RouterModule.forRoot(
            [
                {
                    path: '',
                    pathMatch: 'full',
                    redirectTo: 'auth/login',
                },

                {
                    path: 'home',
                    component: AppLayoutComponent,
                    children: [
                        {
                            path: 'dashboard',
                            loadChildren: () =>
                                import(
                                    './toolkit/dashboard/dashboard.module'
                                ).then((m) => m.DashboardModule),
                        },
                        {
                            path: 'branches',
                            loadChildren: () =>
                                import(
                                    './toolkit/branches/branches.module'
                                ).then((m) => m.BranchesModule),
                        },
                        {
                            path: 'repository',
                            loadChildren: () =>
                                import('./toolkit/repository/repository.module').then(
                                    (m) => m.RespositoryModule
                                ),
                        },
                        {
                            path: 'members',
                            loadChildren: () =>
                                import('./toolkit/members/members.module').then(
                                    (m) => m.MembersModule
                                ),
                        },
                        {
                            path: 'pipelines',
                            loadChildren: () =>
                                import(
                                    './toolkit/pipelines/pipelines.module'
                                ).then((m) => m.PipelinesModule),
                        },
                        {
                            path: 'groups',
                            loadChildren: () =>
                                import('./toolkit/groups/groups.module').then(
                                    (m) => m.GroupsModule
                                ),
                        },
                        {
                            path: 'merge',
                            loadChildren: () =>
                                import('./toolkit/merge/merge.module').then(
                                    (m) => m.MergeModule
                                ),
                        },
                        {
                            path: 'issues',
                            loadChildren: () =>
                                import('./toolkit/issues/issues.module').then(
                                    (m) => m.IssuesModule
                                ),
                        },
                        {
                            path: 'commits',
                            loadChildren: () =>
                                import('./toolkit/commit/commit.module').then(
                                    (m) => m.CommitModule
                                ),
                        },
                    ],
                },
                {
                    path: 'auth',
                    loadChildren: () =>
                        import('./toolkit/auth/auth.module').then(
                            (m) => m.AuthModule
                        ),
                },
                { path: 'notfound', component: NotfoundComponent },
                { path: '**', redirectTo: '/notfound' },
            ],
            {
                scrollPositionRestoration: 'enabled',
                anchorScrolling: 'enabled',
                onSameUrlNavigation: 'reload',
            }
        ),
    ],
    exports: [RouterModule],
})
export class AppRoutingModule {}
