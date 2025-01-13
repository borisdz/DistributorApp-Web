import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserModel } from '../models/user.model';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';
  private userRole: string | null = null;

  setUserRole(role: string): void {
    this.userRole = role;
    localStorage.setItem('userRole', role);
  }

  getUserRole(): string | null {
    if (!this.userRole) {
      this.userRole = localStorage.getItem('userRole');
    }

    return this.userRole;
  }

  clearUserRole(): void {
    this.userRole = null;
    localStorage.removeItem('userRole');
  }

  constructor(private http: HttpClient) {}

  login(credentials: { email: string; password: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, credentials).pipe(
      tap((response: any) => {
        this.userRole = response.role;
        localStorage.setItem('userRole', response.role);
      })
    );
  }

  register(user: UserModel): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user);
  }

  logout(): void {
    this.clearUserRole();
  }

  isLoggedIn(): boolean {
    return !!this.getUserRole();
  }
}
