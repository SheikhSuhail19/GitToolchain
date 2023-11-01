import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { LayoutService } from 'src/app/layout/app.layout.service';
import { IssuesService } from 'src/app/services/issues.service';
import { MergeRequestService } from 'src/app/services/merge-request.service';
import { CommitService } from 'src/app/services/commit.service';
import { BranchService } from '../branches/branch.service';
import { BRANCHES_TEXT } from 'src/utils/constants';

@Component({
    templateUrl: './dashboard.component.html',
})
export class DashboardComponent implements OnInit, OnDestroy {
    repoId: number;
    chartData: any;
    branchNumber: number;
    chartOptions: any;

    subscription!: Subscription;
    mergeCardClasses = {
        class1: 'flex align-items-center justify-content-center bg-blue-100 border-round ml-3',
        class2: 'pi pi-share-alt text-blue-500 text-xl',
    };
    issuesCardClasses = {
        class1: 'flex align-items-center justify-content-center bg-red-100 border-round ml-3',
        class2: 'pi pi-exclamation-circle text-red-500 text-xl',
    };
    commitsCardClasses = {
        class1: 'flex align-items-center justify-content-center bg-orange-100 border-round ml-3',
        class2: 'pi pi-box text-orange-500 text-xl',
    };
    branchesCardClasses = {
        class1: 'flex align-items-center justify-content-center bg-blue-100 border-round ml-3',
        class2: 'pi pi-share-alt text-blue-500 text-xl',
    };
    
    issuesData = {};
    mergeRequestsData = {};
    branchesData = {};
    totalCommitsData = {
        title: 'Total Commits',

        total_count: null,

        Last_24_Hrs: null,

        Last_7_Days: null,

        days: null,
    };

    constructor(
        public layoutService: LayoutService,
        private issueService: IssuesService,
        private mergeRequestsService: MergeRequestService,
        private CommitService: CommitService,
        private branchService: BranchService
    ) {
        this.subscription = this.layoutService.configUpdate$.subscribe(() => {
            this.initChart();
        });

    }

    ngOnInit() {
        if (sessionStorage.getItem("flag")==="false") {
            // Perform the refresh logic here
            window.location.reload();
            
            // Mark the page as refreshed
            sessionStorage.setItem("flag", "true");
          }
        this.initChart();
        this.issueService.getData(this.repoId).subscribe((data) => {
            this.issuesData = data[0];
        });
        this.mergeRequestsService.getData(this.repoId).subscribe((data) => {
            this.mergeRequestsData = data[0];
        });
        this.CommitService.getTotalNumberOfCommits().subscribe((data) => {
            this.totalCommitsData.total_count = data[0];
            this.totalCommitsData.Last_24_Hrs = data[1];
            this.totalCommitsData.Last_7_Days = data[2];
        });
    }

    initChart() {
        const documentStyle = getComputedStyle(document.documentElement);
        const textColor = documentStyle.getPropertyValue('--text-color');
        const textColorSecondary = documentStyle.getPropertyValue(
            '--text-color-secondary'
        );
        const surfaceBorder =
            documentStyle.getPropertyValue('--surface-border');

        this.chartData = {
            labels: [
                'January',
                'February',
                'March',
                'April',
                'May',
                'June',
                'July',
            ],
            datasets: [
                {
                    label: 'First Dataset',
                    data: [65, 59, 80, 81, 56, 55, 40],
                    fill: false,
                    backgroundColor:
                        documentStyle.getPropertyValue('--bluegray-700'),
                    borderColor:
                        documentStyle.getPropertyValue('--bluegray-700'),
                    tension: 0.4,
                },
                {
                    label: 'Second Dataset',
                    data: [28, 48, 40, 19, 86, 27, 90],
                    fill: false,
                    backgroundColor:
                        documentStyle.getPropertyValue('--green-600'),
                    borderColor: documentStyle.getPropertyValue('--green-600'),
                    tension: 0.4,
                },
            ],
        };

        this.chartOptions = {
            plugins: {
                legend: {
                    labels: {
                        color: textColor,
                    },
                },
            },
            scales: {
                x: {
                    ticks: {
                        color: textColorSecondary,
                    },
                    grid: {
                        color: surfaceBorder,
                        drawBorder: false,
                    },
                },
                y: {
                    ticks: {
                        color: textColorSecondary,
                    },
                    grid: {
                        color: surfaceBorder,
                        drawBorder: false,
                    },
                },
            },
        };
    }

    ngOnDestroy() {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
    }
    updateDashboard(repoId: number) {
        this.repoId = repoId;

        /*Write your code here to update the Ui after repository is selected from the Dropdown.
         repoId is the variable storing the project/repository id*/
        this.setIssueData(repoId);
        this.setMergeRequestData(repoId);
        this.setBranchesData(repoId);
    }
    setIssueData(repoId: number) {
        this.issueService.getData(repoId).subscribe((data) => {
            this.issuesData = data[0];
        });
    }
    setMergeRequestData(repoId: number) {
        this.mergeRequestsService.getData(repoId).subscribe((data) => {
            this.mergeRequestsData = data[0];
        });
    }

    /* This function is used to set the data in branch card present on the dashboard.
       This takes the repository id as an input parameter for fetching data related to that repository*/
    setBranchesData(repoID: number) {
        this.branchService.getNumberOfBranches(repoID).subscribe((data) => {
            this.branchNumber = data.totalLength;
            this.branchesData = {
                title: BRANCHES_TEXT,
                total_count: this.branchNumber,
                branch_changed_in_last_24_hour: data.activity_in_24_hours,
            };
        });
    }
}
