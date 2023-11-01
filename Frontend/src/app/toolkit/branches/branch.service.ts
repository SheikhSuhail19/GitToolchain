import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';
import { Branch } from '../../models/Branch';
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
export class BranchService {
    private apiUrl = `${BASE_URL}/repositories`;

    constructor(private http: HttpClient) {}
    getBranches(
        repoID: number,
        page: number
    ): Observable<{ totalLength: number; branches: Branch[] }> {
        return this.http.get<{ totalLength: number; branches: Branch[] }>(
            `${this.apiUrl}/${repoID}/branches?page=${page}`,
            httpOptions
        );
    }

    getSearchedBranches(
        repoID: number,
        branchName: string,
        page: number
    ): Observable<{ totalLength: number; branches: Branch[] }> {
        return this.http.get<{ totalLength: number; branches: Branch[] }>(
            `${this.apiUrl}/${repoID}/branches/search?searchBranch=${branchName}&page=${page}`,
            httpOptions
        );
    }

    getFilters(
        repoID: number,
        filters: any,
        page: number
    ): Observable<{ totalLength: number; branches: Branch[] }> {
        return this.http.get<{ totalLength: number; branches: Branch[] }>(
            `${this.apiUrl}/${repoID}/branches/filter?${
                filters.canPush !== ''
                    ? filters.canPush === 'Yes'
                        ? 'developers_can_push=true'
                        : 'developers_can_push=false'
                    : ''
            }&${
                filters.merged !== ''
                    ? filters.merged === 'Yes'
                        ? 'developers_can_merge=true'
                        : 'developers_can_merge:false'
                    : ''
            }&${
                filters.protected !== ''
                    ? filters.protected === 'Yes'
                        ? 'protected=true'
                        : 'protected:false'
                    : ''
            }&page=${page}`,
            httpOptions
        );
    }

    getDateRange(
        repoID: number,
        startDate: string,
        endDate: string,
        page: number
    ): Observable<{ totalLength: number; branches: Branch[] }> {
        return this.http.get<{ totalLength: number; branches: Branch[] }>(
            `${this.apiUrl}/${repoID}/branches/calendar?start_date=${startDate}&end_date=${endDate}&page=${page}`,
            httpOptions
        );
    }

    deleteBranch(Branch: Branch): Observable<Branch> {
        const url = `${this.apiUrl}/${Branch.name}`;
        return this.http.delete<Branch>(url);
    }
    updateBranchReminder(Branch: Branch): Observable<Branch> {
        const url = `${this.apiUrl}/${Branch.name}`;
        return this.http.put<Branch>(url, Branch, httpOptions);
    }
    addBranch(Branch: Branch): Observable<Branch> {
        return this.http.post<Branch>(this.apiUrl, Branch, httpOptions);
    }

    getAllBranches(
        repoID: number,
        page: number
    ): Observable<{ totalLength: number; branches: Branch[] }> {
        return this.http.get<{ totalLength: number; branches: Branch[] }>(
            `${this.apiUrl}/${repoID}/branches?pagination=false`,
            httpOptions
        );
    }
    getNumberOfBranches(
        repoId: number
    ): Observable<{ totalLength: number; activity_in_24_hours: number }> {
        return this.http.get<{
            totalLength: number;
            activity_in_24_hours: number;
        }>(`${this.apiUrl}/${repoId}/branches/overview`, httpOptions);
    }
}
