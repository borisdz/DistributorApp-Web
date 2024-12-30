import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserModel } from '../models/user.model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiServerUrl = '';

  constructor(private http: HttpClient) {}

  public getUsers(): Observable<UserModel[]> {
    return this.http.get<any>(`${this.apiServerUrl}/users/all`);
  }

  public addUser(user: UserModel): Observable<UserModel> {
    return this.http.post<UserModel>(`${this.apiServerUrl}/users/add`, user);
  }

  public updateUser(user: UserModel): Observable<UserModel> {
    return this.http.put<UserModel>(`${this.apiServerUrl}/users/edit`, user);
  }

  public deleteUser(userId: number): Observable<void> {
    return this.http.delete<void>(
      `${this.apiServerUrl}/users/delete/${userId}`
    );
  }
}
