import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { BASE_URL, DATA_PARAM, GET_ISSUE_TEXT, ISSUE_TEXT, PROJECT_ID } from 'src/utils/constants';

@Injectable({
    providedIn: 'root',
})
export class IssuesService {
    private token = sessionStorage.getItem('accessToken');
    private btoken = this.token.split(' ')[1];
    private apiURL = `${BASE_URL}/${ISSUE_TEXT}/${GET_ISSUE_TEXT}?${DATA_PARAM}=`;
    constructor(private http: HttpClient) {}

    getData(repoId: number): Observable<any> {
        
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
                Authorization: sessionStorage.getItem('accessToken'),
            }),
        };
        return this.http.get<any[]>(this.apiURL + repoId, httpOptions);
    }
}
