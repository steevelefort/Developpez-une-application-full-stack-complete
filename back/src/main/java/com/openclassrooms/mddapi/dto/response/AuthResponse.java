package com.openclassrooms.mddapi.dto.response;

import lombok.Data;

/**
 * Response data for authentication with JWT token
 */
@Data
public class AuthResponse {
  private String token;
}
