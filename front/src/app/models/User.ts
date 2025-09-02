export interface UserResponse {
  id: Number;
  userName: String;
  email: String;
  createdAt: Date;
  updatedAt: Date;
  subscriptions: Number[];
}

export interface UserUpdateResponse {
  id: Number;
  userName: String;
  email: String;
  createdAt: Date;
  updatedAt: Date;
}


