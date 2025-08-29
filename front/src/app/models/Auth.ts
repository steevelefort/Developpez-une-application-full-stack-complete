export interface AuthResponse {
  token: string;
}

export interface UserResponse {
  id: Number,
  userName: String,
  email: String,
  createdAt: Date,
  updatedAt: Date,
  subscriptions: Number[],
}

export interface LoginRequest {
  identifier: string;
  password: string;
}
