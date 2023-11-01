import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { BASE_URL } from 'src/utils/constants';
import { Member } from 'src/app/models/member';

const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: sessionStorage.getItem('accessToken'),
    }),
};

@Injectable({
    providedIn: 'root',
})
export class MemberService {
    private apiUrl = `${BASE_URL}/projects`;

    constructor(private http: HttpClient) {}
    getMembers(
        repoID: number,
        page: number,
        searchText: string
    ): Observable<{ totalLength: number; members: Member[] }> {
        return this.http.get<{ totalLength: number; members: Member[] }>(
            `${this.apiUrl}/${repoID}/members?page=${page}&searchText=${searchText}`,
            httpOptions
        );
    }
}
