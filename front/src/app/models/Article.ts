import { Comment } from './Comment';

export interface Article {
  id: Number,
  title: String,
  content: String,
  themeName: String,
  userName: String,
  createdAt: Date,
  updatedAt: Date,
}

export interface FullArticle {
  id: Number,
  title: String,
  content: String,
  themeName: String,
  userName: String,
  createdAt: Date,
  updatedAt: Date,
  comments: Comment[]
}

export interface ArticleRequest {
  title: String,
  content: String,
  themeId: Number
}
