import { Component } from '@angular/core';
import { Branch } from 'src/app/models/Branch';
import { BranchService } from './branch.service';
import { DEFAULT_LABEL_COLOR, GREY } from 'src/utils/colors';
import { saveAs } from 'file-saver';
import * as Papa from 'papaparse';
@Component({
    selector: 'app-branches',
    templateUrl: './branches.component.html',
    styleUrls: ['./branches.component.css'],
})
export class BranchesComponent {
    branches: Branch[] = [];
    filters = { canPush: '', merged: '', protected: '' };
    dates: Date[];
    totalBranches = 0;
    repoID!: number;
    searchText = '';
    defaultLabelColor = DEFAULT_LABEL_COLOR;
    greyColor = GREY;
    cardLabels: Array<Array<Array<string>>> = new Array<Array<Array<string>>>();

    constructor(private branchService: BranchService) {}

    getBranches = (page: number) => {
        this.branchService.getBranches(this.repoID, page).subscribe({
            next: (data: { branches: Branch[]; totalLength: number }) => {
                data.branches.forEach((branch: Branch) => {
                    this.setCardLabels(branch);
                });
                this.branches = data.branches || [];
                this.totalBranches = data.totalLength || 0;
            },
            error: () => {
                this.branches = [];
                this.totalBranches = 0;
            },
        });
    };

    setCardLabels(branch: Branch) {
        var branchLabel: Array<Array<string>> = new Array<Array<string>>();

        if (branch.default) {
            branchLabel.push(['default', DEFAULT_LABEL_COLOR]);
        } else branchLabel.push(['', '']);

        if (branch.protected) {
            branchLabel.push(['protected', GREY]);
        } else branchLabel.push(['', '']);

        this.cardLabels.push(branchLabel);
    }

    searchBranches(searchText: string, page = 0) {
        this.searchText = searchText;
        this.branchService
            .getSearchedBranches(this.repoID, searchText, page)
            .subscribe((data) => {
                this.branches = data.branches;
                this.totalBranches = data.totalLength;
            });
    }

    onRepoChange(repoID: number) {
        this.repoID = repoID;
        this.getBranches(0);
    }

    OnPageChange(page: number) {
        this.searchText !== ''
            ? this.searchBranches(this.searchText, page)
            : this.dates?.[0] && this.dates?.[1]
            ? this.getDateRangesBranches(page)
            : this.filters.canPush !== '' &&
              this.filters.merged !== '' &&
              this.filters.protected !== ''
            ? this.getBranches(page)
            : this.getFilteredBranches(page);
    }

    onFilterChange(filter: { filterName: string; value: string }) {
        this.filters = {
            ...this.filters,
            ...filter,
        };
        this.getFilteredBranches();
    }

    getFilteredBranches(page = 0) {
        this.branchService
            .getFilters(this.repoID, this.filters, page)
            .subscribe((data) => {
                this.branches = data?.branches || [];
                this.totalBranches = data?.totalLength || 0;
            });
    }

    onDateSelect(dates: Date[]) {
        this.dates = dates;
        this.getDateRangesBranches();
    }

    getDateRangesBranches(page = 0) {
        this.branchService
            .getDateRange(
                this.repoID,
                this.dates[0].toISOString(),
                this.dates[1].toISOString(),
                page
            )
            .subscribe((data) => {
                this.branches = data?.branches || [];
                this.totalBranches = data?.totalLength || 0;
            });
    }

    getTimeDiff(date: string) {
        const dateObj = new Date(date);
        const currentDate = new Date();
        const timestamp = dateObj.getTime();
        const currentTimestamp = currentDate.getTime();
        const timeDifference = currentTimestamp - timestamp;

        const seconds = Math.floor(timeDifference / 1000);
        const minutes = Math.floor(seconds / 60);
        const hours = Math.floor(minutes / 60);
        const days = Math.floor(hours / 24);

        if (days > 0) {
            return `${days} day${days > 1 ? 's' : ''} ago`;
        } else if (hours > 0) {
            return `${hours} hour${hours > 1 ? 's' : ''} ago`;
        } else if (minutes > 0) {
            return `${minutes} minute${minutes > 1 ? 's' : ''} ago`;
        } else {
            return `${seconds} second${seconds > 1 ? 's' : ''} ago`;
        }
    }

    exportCSV(): void {
        this.branchService.getAllBranches(this.repoID, 0).subscribe((data) => {
            const csvData = Papa.unparse(data.branches);
            const timestamp = new Date();

            const blob = new Blob([csvData], {
                type: 'text/csv;charset=utf-8',
            });

            saveAs(blob, `branches_${timestamp}.csv`);
        });
    }
}
