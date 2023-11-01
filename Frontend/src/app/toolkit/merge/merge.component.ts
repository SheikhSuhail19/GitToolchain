import { Component, OnInit, ViewChild } from '@angular/core';
import { saveAs } from 'file-saver';
import * as Papa from 'papaparse';
import { HttpClient } from '@angular/common/http';
import { MergeService } from 'src/app/services/merge.service';
import { EMAIL_REVIEWER, MESSAGE_REVIEWER, MR_TEXT, NOTIFICATION_TEXT, SOPRA_BANKING_EMAIL } from 'src/utils/constants';
import { NotificationService } from 'src/app/services/notification.service';
import { forkJoin } from 'rxjs';
import { Dialog } from 'primeng/dialog';



@Component({
    selector: 'app-merge',
    templateUrl: './merge.component.html',
    styleUrls: ['./merge.component.css'],
})
export class MergeComponent implements OnInit {
    mergeRequests: any[] = [];
    notificationMergeRequests : any[]=[];
    searchQuery: string = '';
    filteredMergeRequests: any[];
    totalRecords: number;
    rows: number = 10; // Number of rows per page
    first: number = 0; // Index of the first row
    repoId: number;
    sopraEmail = SOPRA_BANKING_EMAIL;
    flag: number = 1; // Assume this is obtained from the backend, we cant send notification ,
    isVisible: boolean = false; //for visibility of P-dailog
    mergeRequestId:string;
    selectedReviewerUsername:string;

    tabItems: any[] = [
        {
            label: 'All',
            icon: 'pi pi-list',
            command: () => this.selectTag('All'),
        },
        {
            label: 'Open',
            icon: 'pi pi-exclamation-triangle',
            command: () => this.selectTag('opened'),
        },
        {
            label: 'Merged',
            icon: 'fa fa-code-fork',
            command: () => this.selectTag('merged'),
        },
        {
            label: 'Closed',
            icon: 'pi pi-check',
            command: () => this.selectTag('closed'),
        },
    ];

    selectedTag: string = 'All';

    selectTag(tag: string) {
        this.selectedTag = tag;
        this.filterMergeRequests(tag);
    }


    clearSearchBar(): void {
        this.searchQuery = '';
    }

    ngOnInit(): void {
        this.fetchMergeRequests(this.repoId);
        this.fetchNotifications(this.repoId);
    }

    constructor(
        private httpClient: HttpClient,
        private MergeService: MergeService,
        private NotificationService: NotificationService,
    ) {
        // Initialize with all merge requests
        this.filteredMergeRequests = [...this.mergeRequests];

        this.fetchMergeRequests(this.repoId);
        
    }

    filterMergeRequests(status: string): void {
        this.clearSearchBar();

        if (status === 'All') {
            this.filteredMergeRequests = [...this.mergeRequests];
            this.totalRecords = this.filteredMergeRequests.length;
        } else {
            this.filteredMergeRequests = this.mergeRequests.filter(
                (request) => request.state === status
            );
            this.totalRecords = this.filteredMergeRequests.length;
        }
    }

    dataForCsv: any;

    exportToCSV(): void {
        const csvData = this.filteredMergeRequests.map((request) => ({
            id: request.id,
            iid: request.iid,
            project_id: request.project_id,
            title: request.title,
            author_name: request.author.name,
            time: request.created_at,
            status: request.state,
        }));

        const csvString = Papa.unparse(csvData, {
            header: true,
        });
        const timestamp = new Date();

        const blob = new Blob([csvString], { type: 'text/csv;charset=utf-8' });

        saveAs(blob, `merge-requests_${timestamp}.csv`);
    }

    fetchMergeRequests(repoId: number): void {
        // this.filteredMergeRequests=[];
        this.MergeService.fetchMergeRequests(repoId).subscribe(
            (data: any) => {
                this.mergeRequests = data;
                this.filteredMergeRequests = [...this.mergeRequests];
                this.filteredMergeRequests.forEach((item) => {
                    item.enableNotification = false;
                  });
                  
                this.totalRecords = this.mergeRequests.length;
                this.enableViewIconForBackendRequests(MR_TEXT);
            },
            (error) => {
                console.error('Error fetching merge requests:', error);
            }
        );
        
    }

    fetchNotifications(repoId:number):void{
        
        this.NotificationService.fetchNotificationDetails(repoId).subscribe(
            (data:any) => {
                this.notificationMergeRequests = data;
                
                this.enableViewIconForBackendRequests(NOTIFICATION_TEXT);
            },
            (error)=>{
                console.error("Error fetching notification Merge Requests: "+error);
            }
        )
        
    }
    searchMergeRequests(): void {
        if (this.searchQuery.trim() === '') {
            this.filteredMergeRequests = [...this.mergeRequests];
        } else {
            this.filteredMergeRequests = this.mergeRequests.filter((request) =>
                request.title
                    .toLowerCase()
                    .includes(this.searchQuery.toLowerCase())
            );
        }
    }

    onPageChange(event: any) {
        this.first = event.first;
    }

    paginateMergeRequests() {
        return this.filteredMergeRequests.slice(
            this.first,
            this.first + this.rows
        );
    }
    onRepoChange(repoId) {
        this.repoId = repoId;
        
        this.fetchMergeRequests(repoId);
        this.fetchNotifications(repoId);
    }




    messageReviewer: string = MESSAGE_REVIEWER;
    emailReviewer : string = EMAIL_REVIEWER;
    
    
    selectedReviewer: any={};
    
    
    updateMessage(): void {
    this.messageReviewer = `Hello, ${this.selectedReviewer} Please review the pending merge requests that have been open for more than 7 days.`;
    }
    
    
    isIconDisabled(): boolean {
        return this.flag === 0; 
    }

    

    
    toggleMessage(): boolean {
        return this.flag === 0;

    }




    enableViewIconForBackendRequests(from:String): void {

       
        this.mergeRequests.forEach((request) => {
            const matchingBackendRequest = this.notificationMergeRequests.find(
                (notificationRequest) => notificationRequest.id === request.id
                
            );

            // Check if a matching request is found in the backend data
            if (matchingBackendRequest) {
                // Enable view-icon for this request
                request.enableNotification = true;
            } else {
                // Disable view-icon for this request
                request.enableNotification = false;
            }
        });
    }

    selectedMergeRequest: any;

    showDialog(request: any){

        this.selectedMergeRequest = request;

        this.isVisible = true;

    }
    getReviewersForSelectedMR() {

        if (this.selectedMergeRequest && this.selectedMergeRequest.reviewers){
          return this.selectedMergeRequest.reviewers.map((reviewer) => ({

            label: reviewer.name,

            value: reviewer.username,

          }
          
          ));

        }

        return []; 

      }


    onHideDialog(): void {

        this.isVisible = false;

        this.selectedOption = '';

    }
    selectedOption: string = '';
    @ViewChild('myDialog') myDialog: Dialog;
   

    onSendClick(selectedOption:string):void{
       this.mergeRequestId = this.selectedMergeRequest.id;
        if(selectedOption==="outlook"){
            this.NotificationService.sendNotificationMail(this.mergeRequestId).subscribe(
                (data:any) => {
                    console.log("mail sent ");
                    alert("Mail Sent Successfully!")
                },
                (error)=>{
                    console.error("there is Error while sending mail: "+error.message);
                    alert("Mail Sent Successfully!")
                }
            );
        }
        if(selectedOption==="teams"){
            
            const url = `MSTeams:/l/chat/0/0?users=${this.selectedReviewer}${SOPRA_BANKING_EMAIL}&message=${this.messageReviewer}`;
            
            window.open(url, '_blank');

        }
    }

    
    
}
