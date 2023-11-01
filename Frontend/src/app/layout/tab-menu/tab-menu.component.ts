import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { MenuItem } from 'primeng/api';
import {
    DEFAULT_ORDERBY_VALUE,
    DEFAULT_SORT_VALUE,
    DIFFERENT_SORT_VALUE,
    SEARCH_BRANCH_TEXT,
    SORTING_OPTIONS,
} from 'src/utils/constants';
import { BranchService } from 'src/app/toolkit/branches/branch.service';
@Component({
    selector: 'app-tab-menu',
    templateUrl: './tab-menu.component.html',
    styleUrls: ['./tab-menu.component.css'],
    providers: [],
})
export class TabMenuComponent implements OnInit {
    @Input() isDropdown = true;
    @Input() isSortByDropDown = false;
    @Input() isShowGroup = false;
    searchBoxText = SEARCH_BRANCH_TEXT;
    @Input() tabItems: string[] = [];
    @Input() heading = '';
    @Input() exportCSVButton = false;
    items: MenuItem[] = [];
    sortingOptions = SORTING_OPTIONS;
    selectedSortingOption: string;
    sort = DEFAULT_SORT_VALUE;
    orderBy = DEFAULT_ORDERBY_VALUE;
    value = '';
    activeItem!: MenuItem;

    constructor(private branchService: BranchService) {}

    @Output() searchResult = new EventEmitter<string>();
    @Output() repoChangeEvent = new EventEmitter<number>();
    @Output() sortByEvent = new EventEmitter<{
        sort: string;
        orderBy: string;
    }>();
    @Output() exportCSVEvent = new EventEmitter();

    ngOnInit(): void {
        this.tabItems.forEach((item) => {
            this.items.push({ label: item });
        });

        this.activeItem = this.items[0];
    }

    onSearch() {
        this.searchResult.emit(this.value);
    }

    onRepoChange(repoID: number) {
        this.repoChangeEvent.emit(repoID);
    }

    onOrderByChange(value: string) {
        this.orderBy = value.toLowerCase();
        this.sortByEvent.emit({
            sort: this.sort,
            orderBy: value.toLowerCase(),
        });
    }

    onSortingChange() {
        this.sort === DEFAULT_SORT_VALUE
            ? (this.sort = DIFFERENT_SORT_VALUE)
            : (this.sort = DEFAULT_SORT_VALUE);
        this.sortByEvent.emit({ sort: this.sort, orderBy: this.orderBy });
    }

    onExportCSV(event: any) {
        this.exportCSVEvent.emit(event);
    }
}
