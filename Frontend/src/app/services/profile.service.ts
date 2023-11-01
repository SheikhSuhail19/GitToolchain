import { Injectable } from '@angular/core';
import{HttpClient, HttpHeaders} from '@angular/common/http'
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { BASE_URL } from 'src/utils/constants';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private token = sessionStorage.getItem('accessToken')
  private btoken = this.token.split(" ")[1];
  private apiURL = `${BASE_URL}/user/getUserProfile`

  constructor(private http:HttpClient) {}

  getData():Observable<any>{
    const httpOptions = {
      headers : new HttpHeaders({
        'Content-Type':'application/json',
        'Authorization': sessionStorage.getItem('accessToken'),
      })
    }
    return this.http.get<any[]>(this.apiURL, httpOptions)
  }
}
