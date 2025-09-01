import { HttpClient } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UserResponse } from '../models/User';
import { MessageResponse } from '../models/Message';

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

  public me(): Observable<UserResponse> {
    return this.http.get<UserResponse>(`${this.baseUrl}${this.path}/me`)
      .pipe(tap(userResponse => this._user.set(userResponse)))
  }

  public subscribe(themeId: Number): Observable<MessageResponse> {
    return this.http.post<MessageResponse>(`${this.baseUrl}${this.path}/subscribe/${themeId}`,{})
      .pipe(tap(() => {
        const current = this._user();
        if (current) {
          this._user.set({...current, subscriptions: [...current.subscriptions, themeId]});
        }
      }))
  }

  public unSubscribe(themeId: Number): Observable<MessageResponse> {
    return this.http.post<MessageResponse>(`${this.baseUrl}${this.path}/subscribe/${themeId}`,{})
  }
}
