import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { CommitService } from '../../../services/commit.service';
import { BranchService } from '../../../services/branch.service';
import { saveAs } from 'file-saver';
import * as Papa from 'papaparse';
import { Branch } from 'src/app/models/Branch';
import { PROJECT_ID, NAVIGATE_TO_COMMITS } from 'src/utils/constants';

@Component({
    selector: 'app-branch-list',
    templateUrl: './branch-list.component.html',
    styleUrls: ['./branch-list.component.css'],
})
export class BranchListComponent implements OnInit {
    repoId: number;
    branches: Branch[] = [];
    branchOptions: any[] = [];
    selectedBranch: string = 'master';
    searchText: string = '';
    @Output() repoChange = new EventEmitter();

    constructor(
        private http: HttpClient,
        private router: Router,
        private commitService: CommitService,
        private branchService: BranchService // Inject the BranchService
    ) {}

    @Output() searchQueryChanged = new EventEmitter<string>();

    ngOnInit() {
        // this.fetchBranches();
    }

    fetchBranches(repoId) {
        this.branchService.getBranches(repoId).subscribe((data) => {
            this.branches = data.branches;

            this.branchOptions = this.branches.map((branch) => ({
                label: branch.name,
                value: branch.name,
            }));
        });
    }

    navigateToCommits() {
        if (this.selectedBranch) {
            this.router.navigate([NAVIGATE_TO_COMMITS, this.selectedBranch]);
        }
    }
    searchChanged() {
        this.commitService.changeSearchQuery(this.searchText);
    }

    exportCSV(): void {
        const branchName = this.selectedBranch;

        this.commitService
            .getCommits(PROJECT_ID, branchName)
            .subscribe((commits) => {
                const csvData = Papa.unparse(commits);
                const timestamp = new Date();

                const blob = new Blob([csvData], {
                    type: 'text/csv;charset=utf-8',
                });

                saveAs(blob, `commits_${timestamp}.csv`);
            });
    }
    onRepoChange(repoId: number) {
        this.repoId = repoId;
        this.repoChange.emit(repoId);
        this.fetchBranches(repoId);
    }
}
