import { Injectable, signal } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Article, ArticleRequest } from '../models/Article';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FeedService {

  private baseUrl = environment.apiUrl;
  private path = "/article";

  private _feed = signal<Article[]>([]);
  readonly feed = this._feed.asReadonly();

  constructor(private http: HttpClient) {
  }

  public loadFeed(): Observable<Article[]> {
    return this.http.get<Article[]>(`${this.baseUrl}${this.path}/feed`)
      .pipe(tap(feedResponse => this._feed.set(feedResponse)))
  }

  public create(articleRequest: ArticleRequest): Observable<Article> {
    return this.http.post<Article>(`${this.baseUrl}${this.path}`, articleRequest)
      // .pipe(tap(articleResponse => this._feed.set(articleResponse)))
  }

}
