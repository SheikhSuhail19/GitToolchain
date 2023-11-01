import { Component, Input, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-issue-chart',
  templateUrl: './issue-chart.component.html',
  styleUrls: ['./issue-chart.component.css']
})
export class IssueChartComponent {
  @Input() repoId:number;
  @Input() issueData:any;
  chartOptions = {};
  issueDataPoints = [
    { y: 5, name: 'Closed', color: '#4bd2e9de',indexLabel:'' },
    { y: 6, name: 'Opened', color: '#c092ef',indexLabel:''  },
];

  constructor() {}
  
  ngOnChanges(changes: SimpleChanges) {
   
  

    if(this.repoId!=undefined && this.issueData.hasOwnProperty('closed') ){
      
       
        this.issueDataPoints[0]['y'] = this.issueData['closed'];
        this.issueDataPoints[1]['y'] = this.issueData['opened'];
        
        if(this.issueDataPoints[1]['y']===0 ||  this.issueDataPoints[0]['y'] ===0){
          this.issueDataPoints[0]['y']=0.01
          this.issueDataPoints[1]['y']=0
          this.issueDataPoints[0]['indexLabel']='No issues'
          this.issueDataPoints[1]['indexLabel']='No issues'
          
        }
        else{
          this.issueDataPoints[0]['indexLabel']='Closed'
          this.issueDataPoints[1]['indexLabel']='Opened'
        }
        this.createChart();
      
      
      }
      
  }

  createChart() {
    this.chartOptions = {
      animationEnabled: true,
      title: {
          text: 'ISSUES',
      },
      data: [
          {
              type: 'doughnut',
              startAngle: -90,
              indexLabel: '{name}: {y}',
              dataPoints: this.issueDataPoints,
          },
      ],
  };
  }
 
  ngOnInit() {
    
    this.createChart();
  
  }
}
