import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Repository } from '../../models/Repository';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { BASE_URL } from 'src/utils/constants';

const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: sessionStorage.getItem('accessToken'),
    }),
};
@Injectable({
    providedIn: 'root',
})
export class RepositoryService {
    private apiUrl = `${BASE_URL}/repositories/getdropdownrepos`;

    constructor(private http: HttpClient) {}

    getRepositories(): Observable<Repository[]> {
        return this.http.get<Repository[]>(`${this.apiUrl}`, httpOptions);
    }
}
