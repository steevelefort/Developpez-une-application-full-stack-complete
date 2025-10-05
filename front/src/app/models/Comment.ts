/** Comment data */
export interface Comment {
  id: Number,
  userName: String,
  content: String,
  createdAt: Date,
  updatedAt: Date
}

/** Create comment request */
export interface CommentRequest {
  content: String
}
