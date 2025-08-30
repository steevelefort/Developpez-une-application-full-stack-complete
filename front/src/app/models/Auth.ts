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

export interface RegisterRequest {
  userName: string;
  email: string;
  password: string;
}
