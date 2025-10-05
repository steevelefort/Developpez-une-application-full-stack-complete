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

  constructor(private http: HttpClient) {
  }

  /** Get user feed */
  public loadFeed(): Observable<Article[]> {
    return this.http.get<Article[]>(`${this.baseUrl}${this.path}/feed`)
  }

  /** Create new article */
  public create(articleRequest: ArticleRequest): Observable<Article> {
    return this.http.post<Article>(`${this.baseUrl}${this.path}`, articleRequest)
  }

  /** Get article with comments */
  public getOne(id: String): Observable<FullArticle> {
    return this.http.get<FullArticle>(`${this.baseUrl}${this.path}/${id}`)
  }

  /** Add comment to article */
  public addComment(commentRequest: CommentRequest, articleId: String): Observable<Comment> {
    return this.http.post<Comment>(`${this.baseUrl}${this.path}/${articleId}/comment`, commentRequest)
  }



}



