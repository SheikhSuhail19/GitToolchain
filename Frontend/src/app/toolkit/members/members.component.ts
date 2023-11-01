import { Component, OnInit } from '@angular/core';
import { Member } from 'src/app/models/member';
import { MemberService } from './members.service';
import {
    ROLE_DESCRIPTIONS_DEVELOPER,
    ROLE_DESCRIPTIONS_GUEST,
    ROLE_DESCRIPTIONS_MAINTAINER,
    ROLE_DESCRIPTIONS_MINIMAL_ACCESS,
    ROLE_DESCRIPTIONS_NO_ACCESS,
    ROLE_DESCRIPTIONS_OWNER,
    ROLE_DESCRIPTIONS_REPORTER,
} from 'src/utils/constants';
@Component({
    selector: 'app-members',
    templateUrl: './members.component.html',
    styleUrls: ['./members.component.css'],
})
export class MembersComponent {
    roleDescriptions: { [key: number]: string } = {
        0: ROLE_DESCRIPTIONS_NO_ACCESS,
        5: ROLE_DESCRIPTIONS_MINIMAL_ACCESS,
        10: ROLE_DESCRIPTIONS_GUEST,
        20: ROLE_DESCRIPTIONS_REPORTER,
        30: ROLE_DESCRIPTIONS_DEVELOPER,
        40: ROLE_DESCRIPTIONS_MAINTAINER,
        50: ROLE_DESCRIPTIONS_OWNER,
    };
    searchText = '';
    page = 0;
    value: string | undefined;
    members: Member[] = [];
    totalMembers = 0;
    repoID!: number;
    cols: { field: string; header: string }[];
    constructor(private memberService: MemberService) {}

    getMembers = (page: number) => {
        this.memberService
            .getMembers(this.repoID, page, this.searchText)
            .subscribe({
                next: (data) => {
                    this.members = data.members || [];
                    this.totalMembers = data.totalLength || 0;
                },
                error: () => {
                    this.members = [];
                    this.totalMembers = 0;
                },
            });
    };

    parseDate(created_at: string) {
        const newDate = new Date(created_at);
        return created_at ? newDate.toDateString() : '';
    }

    onRepoChange(repoID: number) {
        this.repoID = repoID;
        this.getMembers(0); // Get first page of selected repository members.
    }

    OnPageChange(page: number) {
        this.getMembers(page);
    }

    searchMembers(searchtext: string) {
        this.searchText = searchtext;
        this.getMembers(0); // Get first page of searched members.
    }
}
