import { Injectable, signal } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Article, ArticleRequest, FullArticle } from '../models/Article';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { Comment, CommentRequest } from '../models/Comment';

@Injectable({
  providedIn: 'root'
})
export class FeedService {

  private baseUrl = environment.apiUrl;
  private path = "/article";

  // private _feed = signal<Article[]>([]);
  // readonly feed = this._feed.asReadonly();

  constructor(private http: HttpClient) {
  }

  public loadFeed(): Observable<Article[]> {
    return this.http.get<Article[]>(`${this.baseUrl}${this.path}/feed`)
      // .pipe(tap(feedResponse => this._feed.set(feedResponse)))
  }

  public create(articleRequest: ArticleRequest): Observable<Article> {
    return this.http.post<Article>(`${this.baseUrl}${this.path}`, articleRequest)
      // .pipe(tap(articleResponse => this._feed.set(articleResponse)))
  }

  public getOne(id: String): Observable<FullArticle> {
    return this.http.get<FullArticle>(`${this.baseUrl}${this.path}/${id}`)
      // .pipe(tap(articleResponse => this._feed.set(articleResponse)))
  }

  public addComment(commentRequest: CommentRequest, articleId: String): Observable<Comment> {
    return this.http.post<Comment>(`${this.baseUrl}${this.path}/${articleId}/comment`, commentRequest)
  }



}



