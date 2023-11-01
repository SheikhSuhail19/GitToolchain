import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
@Injectable({
    providedIn: 'root',
})
export class AuthService {
    constructor() {}

    login(accessToken: string) {
        sessionStorage.setItem('accessToken', `Bearer ${accessToken}`);
        sessionStorage.setItem("flag", "false");
    }

}
