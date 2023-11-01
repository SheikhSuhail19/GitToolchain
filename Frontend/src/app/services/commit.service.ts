import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Commit } from '../models/Commit';
import { BRANCHPARAM, COMMITSPROJECT, BASE_URL,PROJECT_ID, TOTALCOMMITSINPROJECT } from 'src/utils/constants';

const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: sessionStorage.getItem('accessToken')
    }),
};
@Injectable({
    providedIn: 'root'
})
export class CommitService {
    private baseUrl = BASE_URL;

    private searchQuerySource = new BehaviorSubject<string>('');

    currentSearchQuery = this.searchQuerySource.asObservable();

    constructor(private http: HttpClient) {}

    getCommits(projectId: number, branchName: string): Observable<Commit[]> {
        const url = `${this.baseUrl}${COMMITSPROJECT}${projectId}${BRANCHPARAM}${branchName}`;

        return this.http.get<Commit[]>(url, httpOptions);
    }

    changeSearchQuery(query: string) {
        this.searchQuerySource.next(query);
    }
    getTotalNumberOfCommits():Observable<any[]>{

        const url = `${this.baseUrl}${TOTALCOMMITSINPROJECT}${PROJECT_ID}`;

        return this.http.get<any[]>(url, httpOptions);

    }
}
