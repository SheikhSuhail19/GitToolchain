import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import {
    BASE_URL,
    DATA_PARAM,
    GET_MR_COUNT_TEXT,
    MR_TEXT,
    PROJECT_ID,
} from 'src/utils/constants';

@Injectable({
    providedIn: 'root',
})
export class MergeRequestService {
    private token = sessionStorage.getItem('accessToken');
    private btoken = this.token.split(' ')[1];
    private apiURL = `${BASE_URL}/${MR_TEXT}/${GET_MR_COUNT_TEXT}?${DATA_PARAM}=`;
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
