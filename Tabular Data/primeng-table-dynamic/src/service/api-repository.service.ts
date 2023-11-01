import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class ApiRepositoryService {

  constructor(private http: HttpClient) { }

  getData(url: string) {
    const headers = new HttpHeaders({
      'Authorization': 'Bearer your-access-token',
    });

    const options = {
      headers: headers,
    };

    return this.http.get(url, options);


  }
}
