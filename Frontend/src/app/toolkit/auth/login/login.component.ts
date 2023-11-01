import { Component } from '@angular/core';
import { LayoutService } from 'src/app/layout/app.layout.service';
import { AuthService } from 'src/app/services/auth/auth.service';
import { Router } from '@angular/router';
@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styles: [
        `
            :host ::ng-deep .pi-eye,
            :host ::ng-deep .pi-eye-slash {
                transform: scale(1.6);
                margin-right: 1rem;
                color: var(--primary-color) !important;
            }
        `,
    ],
})
export class LoginComponent {
    valCheck: string[] = ['remember'];
    password!: string;
    accessToken: string = '';
    rememberMe: boolean = false; // Initialize rememberMe property to false

    constructor(
        public layoutService: LayoutService,
        private authService: AuthService,
        private router: Router
    ) {}

    login() {
        // Perform your login logic here

        // You can access both accessToken and rememberMe here
        const token = this.accessToken;
        // Save the access token based on "Remember me" checkbox
        this.authService.login(token);
        // Redirect to the dashboard
        this.router.navigate(['/home/dashboard']);
    }
}
