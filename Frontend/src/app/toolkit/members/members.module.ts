import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MembersComponent } from './members.component';
import { TabMenuModule } from 'primeng/tabmenu';
import { TableModule } from 'primeng/table';
import { AvatarModule } from 'primeng/avatar';
import { MemberService } from './members.service';
import { MembersRoutingModule } from './members-routing.module';
import { AvatarComponent } from 'src/app/layout/avatar/avatar.component';
import { FormsModule } from '@angular/forms';
import { AppLayoutModule } from 'src/app/layout/app.layout.module';

@NgModule({
    declarations: [MembersComponent, AvatarComponent],
    imports: [
        CommonModule,
        TabMenuModule,
        TableModule,
        AvatarModule,
        MembersRoutingModule,
        FormsModule,
        AppLayoutModule,
    ],
    providers: [MemberService],
})
export class MembersModule {}
