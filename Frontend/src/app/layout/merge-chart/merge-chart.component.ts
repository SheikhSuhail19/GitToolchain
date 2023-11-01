import { Component, Input ,OnChanges, SimpleChanges} from '@angular/core';
import { MergeRequestService } from 'src/app/services/merge-request.service';

@Component({
  selector: 'app-merge-chart',
  templateUrl: './merge-chart.component.html',
  styleUrls: ['./merge-chart.component.css']
})
export class MergeChartComponent implements OnChanges{
  @Input() repoId:number;
  @Input() mergeData:any;
  chartOptions = {};
  mergeDataPoints = [
      { y: 1, name: 'Closed', color: `#ffdb5b` },
      { y: 2, name: 'Open', color: '#FF91D9' },
      { y: 3, name: 'Merged', color: '#87A9FF' },
  ];

  constructor() {}
  
  ngOnChanges(changes: SimpleChanges) {



    if(this.repoId!=undefined && this.mergeData.hasOwnProperty('closed') ){
        
      this.mergeDataPoints[0]['y'] = this.mergeData['closed'];
      this.mergeDataPoints[1]['y'] = this.mergeData['merged'];
      this.mergeDataPoints[2]['y'] = this.mergeData['opened'];

      this.createChart();
  };
  }

  createChart() {
      this.chartOptions = {
          animationEnabled: true,
          title: {
              text: 'MERGE REQUEST',
          },
          data: [
              {
                  type: 'pie',
                  startAngle: -90,
                  indexLabel: '{name}: {y}',
                  dataPoints: this.mergeDataPoints,
              },
          ],
      };
  }
 
  ngOnInit() {
    
    this.createChart();
  
  }
}
