import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { Merge } from '../models/merge';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';
import { BASE_URL, MERGEURL, PROJECT_ID } from 'src/utils/constants';

const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: sessionStorage.getItem('accessToken'),
    }),
};
@Injectable({
    providedIn: 'root',
})
export class MergeService {
    constructor(private http: HttpClient) {}

    fetchMergeRequests(repoId: number): Observable<Merge[]> {
        const url = `${BASE_URL}${MERGEURL}${repoId}`;

        return this.http.get<Merge[]>(url, httpOptions);
    }
    
}
