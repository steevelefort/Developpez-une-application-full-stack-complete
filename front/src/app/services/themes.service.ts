import { HttpClient } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Theme } from '../models/Theme';

@Injectable({
  providedIn: 'root'
})
export class ThemesService {

  private baseUrl = environment.apiUrl;
  private path = "/theme";

  private _themes = signal<Theme[]>([]);
  readonly themes = this._themes.asReadonly();

  constructor(private http: HttpClient) {
  }

  public loadthemes(): Observable<Theme[]> {
    return this.http.get<Theme[]>(`${this.baseUrl}${this.path}`)
      .pipe(tap(themesResponse => this._themes.set(themesResponse)))
  }

}
