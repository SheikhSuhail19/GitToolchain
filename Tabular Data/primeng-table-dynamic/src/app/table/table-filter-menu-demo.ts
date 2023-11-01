import { Component, Input, OnInit } from '@angular/core';
import { Table } from 'primeng/table';
import { ApiRepositoryService } from 'src/service/api-repository.service';

@Component({
    selector: 'table-filter-menu-demo',
    templateUrl: 'table-filter-menu-demo.html',
    styleUrls: ['table-filter-menu-demo.scss']
})
export class TableFilterMenuDemo implements OnInit {
    private baseUrl = 'http://localhost:8080/api/team2/v1';

    @Input() id: string;

    constructor(private apiRepositoryService: ApiRepositoryService) { }

    first: number = 0;
    rows: number = 10;
    totalRecords: number = 0;
    allBranchData: any[] = [];
    pagedBranchData: any[] = [];
    filteredBranchData: any[] = [];
    searchTerm: string = '';
    placeholderText: string = 'Search...';

    onPageChange(event: PageEvent) {
        this.first = event.first;
        this.rows = event.rows;
        this.updatePage();
    }

    ngOnInit(): void {
        if (this.id) {
            const branchUrl = `${this.baseUrl}/projects/${this.id}/branches/all/details`;

            this.apiRepositoryService.getData(branchUrl).subscribe((data: any[]) => {
                this.allBranchData = data;
                this.totalRecords = data.length;
                this.updatePage();
            });
        } else {
            console.error('id is undefined');
        }
    }


    updatePage() {
        let last = this.first + this.rows > this.totalRecords ? this.totalRecords : this.first + this.rows;
        this.pagedBranchData = this.allBranchData.slice(this.first, last);
    }

    applyFilter() {
        this.filteredBranchData = this.allBranchData.filter((e) =>
            e.text.toLowerCase().includes(this.searchTerm.toLowerCase())
        );
    }

    clear(table: Table) {
        table.clear();
        this.searchTerm = '';
        this.placeholderText = 'Search...';
    }
}

interface PageEvent {
    first: number;
    rows: number;
    page: number;
    pageCount: number;
}
