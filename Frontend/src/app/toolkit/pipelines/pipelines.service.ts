import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { BASE_URL, PIPELINES_TEXT, PROJECTS_TEXT } from 'src/utils/constants';
import { Pipeline } from 'src/app/models/Pipeline';

const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: sessionStorage.getItem('accessToken'),
    }),
};

@Injectable({
    providedIn: 'root',
})
export class PipelineService {
    constructor(private http: HttpClient) {}
    getPipelines(
        repoID: number,
        page: number
    ): Observable<{ totalLength: number; pipelines: Pipeline[] }> {
        return this.http.get<{ totalLength: number; pipelines: Pipeline[] }>(
            `${BASE_URL}/${PROJECTS_TEXT}/${repoID}/${PIPELINES_TEXT}?page=${page}`,
            httpOptions
        );
    }
}
