/** Auth response with JWT token */
export interface AuthResponse {
  token: string;
}

/** Login request */
export interface LoginRequest {
  identifier: string;
  password: string;
}

/** Register request */
export interface RegisterRequest {
  userName: string;
  email: string;
  password: string;
}
