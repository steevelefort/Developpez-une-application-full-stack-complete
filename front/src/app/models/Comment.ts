export interface Comment {
  id: Number,
  userName: String,
  content: String,
  createdAt: Date,
  updatedAt: Date
}

export interface CommentRequest {
  content: String
}
