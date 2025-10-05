import { Comment } from './Comment';

/** Article data */
export interface Article {
  id: Number,
  title: String,
  content: String,
  themeName: String,
  userName: String,
  createdAt: Date,
  updatedAt: Date,
}

/** Article with comments */
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

/** Create article request */
export interface ArticleRequest {
  title: String,
  content: String,
  themeId: Number
}
