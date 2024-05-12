import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import { StringResponse } from '../../interface/login-response';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8081/users/';

  constructor(private http: HttpClient) { }

  signin(username: string, password: string): Observable<StringResponse> {
    return this.http.post<StringResponse>(`${this.apiUrl}signin`, { username, password });
  }

  signup(userObj: any): Observable<StringResponse> {
    return this.http.post<StringResponse>(`${this.apiUrl}signup`, userObj);
  }

  sendVerificationCode(emailObj: any): Observable<StringResponse> {
    return this.http.post<StringResponse>(`${this.apiUrl}verification/email`, emailObj);
  }

  resetPassword(resetObj: any): Observable<StringResponse> {
    return this.http.post<StringResponse>(`${this.apiUrl}reset/password`, resetObj);
  }
}
