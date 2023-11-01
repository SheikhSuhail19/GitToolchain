import { Component , OnInit} from '@angular/core';
import { RepositoryService } from '../../services/repository-contributors.service'

@Component({
  selector: 'app-repository',
  templateUrl: './repository.component.html',
  styleUrls: ['./repository.component.css']
})
export class RepositoryComponent implements OnInit {


  repositories = [];

  constructor(private repositoryService: RepositoryService ){};

  
  ngOnInit(): void {
    this.getRepoContributors();
  }

  getRepoContributors(){
    this.repositoryService.getData().subscribe((data)=>{
      for (let index = 0; index < data.length; index++) {
        this.repositories.push(data[index]);
        
      }
      console.log(this.repositories);
    })
  }



}
