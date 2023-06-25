import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl = 'http://localhost:8080/users';

  constructor(private http: HttpClient) {}

  createUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/register`, user);
  }

  getUser(userId: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${userId}`);
  }

  updateUser(userId: number, user: User): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/${userId}`, user);
  }

  deleteUser(userId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${userId}`);
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}`);
  }

  getUserProfile(email: string): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/profile/${email}`);
  }

  authenticate(user: User): Observable<any> {
    return this.http.post(`${this.apiUrl}/auth`, user);
  }

  logout(): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/logout`, {});
  }
}
