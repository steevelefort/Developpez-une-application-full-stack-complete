import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthResponse } from '../models/Auth';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = "http://localhost:8080/";
  private path = "api/auth/";
  private token: String | null = null;

  constructor(private http: HttpClient) {
  }

  public login(login, password): Boolean {
    this.http.post<AuthResponse>(`${this.baseUrl}+${this.path}`, );
    return true;
  }



}
