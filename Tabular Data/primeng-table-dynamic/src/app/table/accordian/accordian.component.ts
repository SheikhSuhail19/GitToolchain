// import { ChangeDetectorRef, Component, OnInit, QueryList, ViewChildren } from '@angular/core';
// import { Table } from 'primeng/table';
// import { ApiRepositoryService } from '../../../service/api-repository.service';
// import { Accordion, AccordionTab } from 'primeng/accordion';


// @Component({
//   selector: 'app-accordian',
//   templateUrl: './accordian.component.html',
//   styleUrls: ['./accordian.component.scss']
// })

// export class AccordianComponent implements OnInit {

//   private baseUrl = 'http://localhost:8080/api/team2/v1';

//   constructor(private apiRepositoryService: ApiRepositoryService, private cdr: ChangeDetectorRef) { }

//   first: number = 0;
//   rows: number = 10;
//   totalRecords: number = 0;
//   allRepoData: any[] = [];
//   pagedRepoData: any[] = [];
//   filteredRepoData: any[] = [];
//   searchTerm: string = '';
//   placeholderText: string = 'Search...';

//   allBranchesData: any = {};
//   // allBranchesData: any;

//   currentOpenTabIndex: number = -1;


//   accordionData: { [sectionId: string]: any } = {};


//   onPageChange(event: PageEvent) {
//     this.first = event.first;
//     this.rows = event.rows;
//     this.updatePage();
//   }

//   ngOnInit(): void {
//     const url = this.baseUrl + "/projects/all/details";
//     this.apiRepositoryService.getData(url).subscribe(response => {
//       // console.log(response)
//       if (Array.isArray(response)) {
//         this.allRepoData = response;
//         // console.log(this.allData)

//       } else {
//         console.error('Invalid data format:', response);
//         this.allRepoData = [];
//       }
//       this.totalRecords = this.allRepoData.length;
//       this.updatePage();
//       // console.log(this.allRepoData)
//     });
//   }


//   updatePage() {
//     let last = this.first + this.rows > this.totalRecords ? this.totalRecords : this.first + this.rows;
//     this.pagedRepoData = this.allRepoData.slice(this.first, last);
//     // console.log(this.pagedRepoData)
//   }

//   applyFilter() {
//     this.filteredRepoData = this.allRepoData.filter(e =>
//       e && e.name && typeof e.name === 'string' &&
//       e.name.toLowerCase().includes(this.searchTerm.toLowerCase())
//     );
//     // console.log(this.filteredRepoData)
//   }


//   // onAccordionTabClick(section: any) {
//   //   const url = `${this.baseUrl}/projects/${section.id}/branches/all/details`;

//   //   this.apiRepositoryService.getData(url).subscribe((data) => {
//   //     // console.log(data)
//   //     this.allBranchesData = data;
//   //     this.cdr.detectChanges();
//   //     // console.log(this.allBranchesData)

//   //   });
//   //   // console.log('Accordion tab clicked:', section);
//   // }

//   // onAccordionTabClick(section: any) {
//   //   const url = `${this.baseUrl}/projects/${section.id}/branches/all/details`;

//   //   this.apiRepositoryService.getData(url).subscribe((data) => {
//   //     // Log the data received from the API in parent component
//   //     console.log('Data received in AccordianComponent:', data);

//   //     // Update the data for the specific accordion tab
//   //     this.accordionData[section.id] = data;

//   //     // You can optionally trigger change detection here if needed
//   //     this.cdr.detectChanges();
//   //   });
//   // }

//   onAccordionTabClick(section: any, index: number) {

//     console.log('Accordion Tab Clicked:', section, 'Index:', index);
//     if (this.currentOpenTabIndex !== index) {
//       this.currentOpenTabIndex = index;
//       const url = `${this.baseUrl}/projects/${section.id}/branches/all/details`;

//       this.apiRepositoryService.getData(url).subscribe((data) => {
//         // this.allBranchesData = data;
//         this.accordionData[section.id] = data;
//         // console.log(this.allBranchesData)
//         this.cdr.detectChanges();
//       });
//     }
//   }




//   clear() {
//     this.searchTerm = '';
//     this.placeholderText = 'Search...';
//   }


// }

// interface PageEvent {
//   first: number;
//   rows: number;
//   page: number;
//   pageCount: number;
// }


import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ApiRepositoryService } from '../../../service/api-repository.service';

@Component({
  selector: 'app-accordian',
  templateUrl: './accordian.component.html',
  styleUrls: ['./accordian.component.scss']
})
export class AccordianComponent implements OnInit {

  private baseUrl = 'http://localhost:8080/api/team2/v1';

  constructor(private apiRepositoryService: ApiRepositoryService, private cdr: ChangeDetectorRef) { }

  first: number = 0;
  rows: number = 10;
  totalRecords: number = 0;
  allRepoData: any[] = [];
  pagedRepoData: any[] = [];
  filteredRepoData: any[] = [];
  searchTerm: string = '';
  placeholderText: string = 'Search...';

  currentOpenTabIndex: number = -1;
  activeSection: any;

  accordionData: { [sectionId: string]: any } = {};

  onPageChange(event: PageEvent) {
    this.first = event.first;
    this.rows = event.rows;
    this.updatePage();
  }

  ngOnInit(): void {
    const url = this.baseUrl + "/projects/all/details";
    this.apiRepositoryService.getData(url).subscribe(response => {
      if (Array.isArray(response)) {
        this.allRepoData = response;
      } else {
        console.error('Invalid data format:', response);
        this.allRepoData = [];
      }
      console.log(this.allRepoData)
      this.totalRecords = this.allRepoData.length;
      this.updatePage();
    });
  }

  updatePage() {
    let last = this.first + this.rows > this.totalRecords ? this.totalRecords : this.first + this.rows;
    this.pagedRepoData = this.allRepoData.slice(this.first, last);
  }

  applyFilter() {
    this.filteredRepoData = this.allRepoData.filter(e =>
      e && e.name && typeof e.name === 'string' &&
      e.name.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  onAccordionTabClick(section: any, index: number) {
    if (this.currentOpenTabIndex !== index) {
      this.currentOpenTabIndex = index;
      this.activeSection = section;
    }
  }

  clear() {
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
