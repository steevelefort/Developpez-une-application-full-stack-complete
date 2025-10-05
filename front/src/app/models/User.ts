/** User data with subscriptions */
export interface UserResponse {
  id: Number;
  userName: String;
  email: String;
  createdAt: Date;
  updatedAt: Date;
  subscriptions: Number[];
}

/** User data after update */
export interface UserUpdateResponse {
  id: Number;
  userName: String;
  email: String;
  createdAt: Date;
  updatedAt: Date;
}


