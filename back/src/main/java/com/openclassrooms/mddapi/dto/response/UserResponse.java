package com.openclassrooms.mddapi.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/**
 * Response data for user information
 */
@Data
public class UserResponse {
  private Long id;

  private String userName;

  private String email;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  private List<Long> subscriptions;
}
