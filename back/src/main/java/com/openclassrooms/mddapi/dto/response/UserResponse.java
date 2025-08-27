package com.openclassrooms.mddapi.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserResponse {
  private Long id;

  private String userName;

  private String email;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
