import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { BASE_URL } from '../../utils/constants';
import { Branch } from '../models/Branch';
import { map } from 'rxjs/operators';
import { REPOSITORY_ENDPOINT, BRANCHES_ENDPOINT, BRANCHES_OVERVIEW } from 'src/utils/constants';

const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: sessionStorage.getItem('accessToken'),
    }),
};
@Injectable({
    providedIn: 'root',
})
export class BranchService {
    private apiUrl = BASE_URL;

    constructor(private http: HttpClient) {}

    getBranches(projectId: number): Observable<{ branches: Branch[] }> {
        return this.http.get<{ branches: Branch[] }>(
            `${this.apiUrl}${REPOSITORY_ENDPOINT}${projectId}${BRANCHES_ENDPOINT}?pagination=false`,
            httpOptions
        ).pipe(
            map(result => result ? result :{ branches : [] })
        );
    }
}
