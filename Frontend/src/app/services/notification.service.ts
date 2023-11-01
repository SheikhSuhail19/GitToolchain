import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { BASE_URL, MAILSENDINGURL, NOTIFICATIONURL} from 'src/utils/constants';

const httpOptions = {
  headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: sessionStorage.getItem('accessToken'),
  }),
};
@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  projectId:string;

  constructor(private http: HttpClient) { }

  fetchNotificationDetails(repoId:number):Observable<any[]>{
    // const url = 'http://localhost:8080/merge/filteredMergeRequest?projectId=112942'

    this.projectId = repoId.toString();
    const url = `${BASE_URL}${NOTIFICATIONURL}${this.projectId}`;

    return this.http.get<any[]>(url, httpOptions);

  }
  sendNotificationMail(mergeId:string):Observable<string>{
    const url = `${BASE_URL}${MAILSENDINGURL}${mergeId}`
    // const url = "http://localhost:8080/notification/send?id=463287"

    return this.http.get<string>(url);
    
  }
}
