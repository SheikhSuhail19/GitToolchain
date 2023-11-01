import { Component, OnInit } from '@angular/core';
import { IssueMenuService } from '../../services/issue-menu.service'
import { IssuesService } from '../../services/issues.service'

@Component({
  selector: 'app-issues',
  templateUrl: './issues.component.html',
  styleUrls: ['./issues.component.css']
})
export class IssuesComponent implements OnInit  {
  
  globalIssuesList = [];
  constructor(private issueMenuService: IssueMenuService , private issueService : IssuesService){};
  selectedLabels : string[] = ["all"];
  state : string = "all";
  issues = [];
  totalIssues:number;

  labels = [{"label":"All"}];
  states = [{state:"Opened"},{state: "Closed"}];

  sortingCriteria = [
    { by : "Created Date" },
    { by : "Updated Date" },
    { by : "Upvoted" },
  ]

  repoId : number;

  


  noOfIssues : number;


  ngOnInit(): void {
    this.fetchIssues(this.repoId);
  }

  fetchIssues(repoId):void{
    this.issues = [];
    this.globalIssuesList = []
    this.issueMenuService.getData(repoId , this.state,this.selectedLabels).subscribe((data) =>{
      for (let index = 0; index < data.length; index++) {
        this.issues.push(data[index]);
        this.globalIssuesList.push(data[index]);
      }
      this.noOfIssues = this.issues.length;
    })
  }


  fetchAllLabels(repoId:number):void{
    this.issueService.getData(repoId).subscribe((data)=>{
      let labelsArray = Object.keys(data[0].labels);
      for (let index = 0; index < labelsArray.length; index++) {
        this.labels.push({"label": labelsArray[index]});
      }
    })
    console.log(this.labels)
  }

  onRepoChange(repoId:number){
    this.repoId = repoId;
    this.issues = [];
    this.globalIssuesList = [];
    this.fetchIssues(this.repoId)
    this.fetchAllLabels(repoId);
  }

  sorting(data){
    
    if (data.value == null){
      this.fetchIssues(this.repoId)
    }
    else if (data.value.by == "Created Date") {
       this.issues.sort((i1, i2)=>
        (i1.created_at > i2.created_at) ? 1 : (i1.created_at < i2.created_at) ? -1:0 
       )
    } else if (data.value.by == "Updated Date") {
      this.issues.sort((i1, i2)=>
      (i1.updated_at > i2.updated_at) ? 1 : (i1.updated_at < i2.updated_at) ? -1:0 
     )
    }else if (data.value.by == "Upvoted"){
      this.issues.sort((i1, i2)=>
      (i1.upvotes < i2.upvotes) ? 1 : (i1.upvotes > i2.upvotes) ? -1:0 
     )
    }
  }

  sortByState(data){
   
   if (data.value == null){
      this.state = "all";
    }
    else if(data.value.state === "Opened"){
      this.state = "opened";
    }else if(data.value.state === "Closed"){
      this.state = "closed"
    }
    this.issues = [];
    this.fetchIssues(this.repoId);
  }
  
  onSearch(data){
   this.issues.filter((i)=>{return i.author.username == data})
  }

  filterByLabels(data){
    if (data.value.label == "All") {
      this.fetchIssues(this.repoId);
    }
    this.issues = [];
    for (let index = 0; index < this.globalIssuesList.length; index++) {
      for (let i = 0; i < this.globalIssuesList[index].labels.length; i++) {
        if(this.globalIssuesList[index].labels[i] == data.value.label){
          this.issues.push(this.globalIssuesList[index]);
          break;
        }
      }
    }
  }


  searchByAuthor(data){
    if (data == "") {
      this.fetchIssues(this.repoId);
    }
    this.issues = [];
    for (let index = 0; index < this.globalIssuesList.length; index++) {
      for (let i = 0; i < this.globalIssuesList[index].labels.length; i++) {
        if(this.globalIssuesList[index].author.username == data){
          this.issues.push(this.globalIssuesList[index]);
          break;
        }
      }
    }
  }

}
