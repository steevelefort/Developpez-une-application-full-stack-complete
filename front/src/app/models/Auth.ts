export interface AuthResponse {
  token: string;
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
