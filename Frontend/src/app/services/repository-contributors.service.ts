import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { BASE_URL, REPO_CONTRIBUTORS } from 'src/utils/constants';

@Injectable({
    providedIn: 'root',
})
export class RepositoryService {
    private token = sessionStorage.getItem('accessToken');
    private apiURL = `${BASE_URL}${REPO_CONTRIBUTORS}`;
    constructor(private http: HttpClient) {}

    getData(): Observable<any> {
        
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
                Authorization: sessionStorage.getItem('accessToken'),
            }),
        };
        return this.http.get<any[]>(this.apiURL, httpOptions);
    }
}
