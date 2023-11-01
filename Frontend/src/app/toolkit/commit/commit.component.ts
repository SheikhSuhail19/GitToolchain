import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { CommitService } from '../../services/commit.service';
import { Commit } from '../../models/Commit';
import {
    COPY,
    PROJECT_ID,
    SHA_COPY_ALERT,
    TEXT_AREA,
    NAVIGATE_TO_COMMITS
} from 'src/utils/constants';
import {Router} from '@angular/router'
@Component({
    selector: 'app-commit',
    templateUrl: './commit.component.html',
    styleUrls: ['./commit.component.css'],
})
export class CommitComponent implements OnInit {
    commits: Commit[] = [];
    branchName: string = '';

    totalCommits: Commit[] = [];
    searchQuery: string = '';
    pageSize: number = 10; // Number of commits per page
    currentPage: number = 1; // Current page number
    totalPages: number = 0;
    repoId: number;

    constructor(
        private http: HttpClient,
        private route: ActivatedRoute,
        private commitService: CommitService,
        private router: Router
    ) {}

    ngOnInit() {
        this.router.navigate([NAVIGATE_TO_COMMITS, "master"]);
        this.route.params.subscribe((params) => {
            this.branchName = decodeURIComponent(params['branchName']);

            this.currentPage = 1;
            this.fetchCommits(this.repoId);
        });
        this.commitService.currentSearchQuery.subscribe((query) => {
            this.searchQuery = query;
            this.filterCommits();
        });
    }

    fetchCommits(repoId: number) {
        this.commitService
            .getCommits(repoId, this.branchName)
            .subscribe((data) => {
                this.commits = data;
                this.totalCommits = data;
                this.totalPages = Math.ceil(
                    this.commits.length / this.pageSize
                );
            });
    }

    onPageChange(event) {
        this.currentPage = event.page + 1;
    }
    nextPage() {
        if (this.currentPage < this.totalPages) {
            this.currentPage++;
        }
    }

    prevPage() {
        if (this.currentPage > 1) {
            this.currentPage--;
        }
    }

    get pagedCommits(): any[] {
        const startIndex = (this.currentPage - 1) * this.pageSize;
        const endIndex = startIndex + this.pageSize;
        return this.commits.slice(startIndex, endIndex);
    }

    filterCommits() {
        if (this.searchQuery) {
            this.commits = this.commits.filter((commit) =>
                commit.title
                    .toLowerCase()
                    .includes(this.searchQuery.toLowerCase())
            );
            this.totalPages = Math.ceil(this.commits.length / this.pageSize);
        } else {
            this.commits = this.totalCommits;
            this.totalPages = Math.ceil(this.commits.length / this.pageSize);
        }
    }

    copySHA(sha: string) {
        const textArea = document.createElement(TEXT_AREA);
        textArea.value = sha;
        document.body.appendChild(textArea);
        textArea.select();
        document.execCommand(COPY);
        document.body.removeChild(textArea);

        alert(SHA_COPY_ALERT + sha);
    }
    repoChange(repoId: number) {
        this.repoId = repoId;
        this.fetchCommits(repoId);
    }
}
