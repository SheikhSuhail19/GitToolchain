import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { BASE_URL } from 'src/utils/constants';
import { Group } from 'src/app/models/Group';

const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: sessionStorage.getItem('accessToken'),
    }),
};
@Injectable({
    providedIn: 'root',
})
export class GroupService {
    private apiUrl = `${BASE_URL}/groups`;

    constructor(private http: HttpClient) {}
    getGroups(
        searchText: string,
        page: number,
        sort: string,
        orderBy: string
    ): Observable<{ groups: Group[]; totalLength: number }> {
        return this.http.get<{ groups: Group[]; totalLength: number }>(
            `${this.apiUrl}?search=${searchText}&page=${page}&sort=${sort}&order_by=${orderBy}`,
            httpOptions
        );
    }
}
