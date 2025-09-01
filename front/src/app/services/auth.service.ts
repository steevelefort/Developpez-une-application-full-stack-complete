import { HttpClient } from '@angular/common/http';
import { computed, Injectable, signal } from '@angular/core';
import { AuthResponse, LoginRequest, RegisterRequest} from '../models/Auth';
import { Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = environment.apiUrl;
  private path = "/auth";

  private _token = signal<string | null>(localStorage.getItem('token'));
  readonly token = this._token.asReadonly();
  readonly isAuthenticated = computed(() => !!this._token());


  constructor(private http: HttpClient) {
  }

  private setAndSaveToken(token: string | null) {
    this._token.set(token);
    if (token === null) {
      localStorage.removeItem('token');
    } else {
      localStorage.setItem('token', token);
    }
  }

  public login(loginRequest: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.baseUrl}${this.path}/login`, loginRequest)
      .pipe(tap(authResponse => this.setAndSaveToken(authResponse.token)))
  }

  public register(registerRequest: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.baseUrl}${this.path}/register`, registerRequest)
      .pipe(tap(authResponse => this.setAndSaveToken(authResponse.token)))
  }

  public logout(): void {
    this.setAndSaveToken(null);
  }


}
