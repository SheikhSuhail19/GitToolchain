import { Component, OnInit } from '@angular/core';
import { Group } from 'src/app/models/Group';
import { GroupService } from './group.service';

@Component({
    selector: 'app-groups',
    templateUrl: './groups.component.html',
    styleUrls: ['./groups.component.css'],
})
export class GroupsComponent implements OnInit {
    groups: Group[] = [];
    totalLength = 0;
    searchText = '';
    sort = 'asc';
    orderBy = 'name';
    page = 0;

    constructor(private groupService: GroupService) {}

    ngOnInit(): void {
        this.getGroups('', 0, 'asc', 'name');
    }

    getGroups = (
        searchText: string,
        page: number,
        sort: string,
        orderBy: string
    ) => {
        this.groupService.getGroups(searchText, page, sort, orderBy).subscribe({
            next: (data) => {
                this.groups = data?.groups || [];
                this.totalLength = data?.totalLength || 0;
            },
            error: () => {
                this.groups = [];
                this.totalLength = 0;
            },
        });
    };

    searchGroups(searchtext: string) {
        this.getGroups(searchtext, 0, this.sort, this.orderBy);
    }

    onSort(sortingOptions: { sort: string; orderBy: string }) {
        this.getGroups(
            this.searchText,
            0,
            sortingOptions.sort,
            sortingOptions.orderBy
        );
    }

    onPageChange(page: number) {
        this.getGroups(this.searchText, page, this.sort, this.orderBy);
    }
}
