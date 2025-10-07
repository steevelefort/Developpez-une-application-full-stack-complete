import { HttpClient } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UserResponse, UserUpdateResponse } from '../models/User';
import { MessageResponse } from '../models/Message';
import { AuthResponse, RegisterRequest } from '../models/Auth';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = environment.apiUrl;
  private path = "/user";

  private _user = signal<UserResponse | null>(null);
  readonly user = this._user.asReadonly();

  constructor(private http: HttpClient) {
  }

  /** Get current user data */
  public me(): Observable<UserResponse> {
    return this.http.get<UserResponse>(`${this.baseUrl}${this.path}/me`)
      .pipe(tap(userResponse => this._user.set(userResponse)))
  }

  /** Clear user data */
  public clear(): void {
    this._user.set(null);
  }

  /** Subscribe to theme */
  public subscribe(themeId: Number): Observable<MessageResponse> {
    return this.http.post<MessageResponse>(`${this.baseUrl}${this.path}/subscribe/${themeId}`,{})
      .pipe(tap(() => {
        const current = this._user();
        if (current) {
          this._user.set({...current, subscriptions: [...current.subscriptions, themeId]});
        }
      }))
  }

  /** Unsubscribe from theme */
  public unSubscribe(themeId: Number): Observable<MessageResponse> {
    return this.http.delete<MessageResponse>(`${this.baseUrl}${this.path}/subscribe/${themeId}`,{})
      .pipe(tap(() => {
        const current = this._user();
        if (current) {
          this._user.set({...current, subscriptions: current.subscriptions.filter((id) => id !== themeId)});
        }
      }))
  }

  /** Update user profile */
  public update(updateRequest: RegisterRequest): Observable<UserUpdateResponse> {
    return this.http.put<UserUpdateResponse>(`${this.baseUrl}${this.path}/update`, updateRequest)
      .pipe(tap(userResponse => {
        const current = this._user();
        if (current) {
          this._user.set({
            ...current,
            userName: userResponse.userName,
            email: userResponse.email
          });
        }
      }))
  }
}
