import { Injectable } from '@angular/core';

import{HttpClient, HttpHeaders} from '@angular/common/http'

import { Observable ,of} from 'rxjs';

import { environment } from 'src/environments/environment';

import { BASE_URL, PROJECT_ID , ISSUE_PAGE_ENDPOINT} from 'src/utils/constants';

 

@Injectable({

  providedIn: 'root'

})

export class IssueMenuService {

  private token = sessionStorage.getItem('accessToken')

  private btoken = this.token.split(" ")[1];

  private apiURL = `${BASE_URL}${ISSUE_PAGE_ENDPOINT}?projectId=`

  constructor(private http:HttpClient) { }

 

  getData(projectId:number , state?: string, labels?: string[]):Observable<any>{



    let newState: string ="";

    let labelList: string ="";

 

    const httpOptions = {

      headers : new HttpHeaders({
        'Content-Type':'application/json',
        'Authorization': sessionStorage.getItem('accessToken'),
      })
    }

    if(typeof state !=='undefined' && state!=="") {

      newState=`&state=${state}`;

    }

    if(typeof labels !=='undefined' && labels.length!==0) {

      labelList=`&labels=${labels.join(',')}`;

    }
    console.log(this.apiURL+newState+labelList, httpOptions);
    return this.http.get<any[]>(this.apiURL+projectId+newState+labelList, httpOptions);

  }

}