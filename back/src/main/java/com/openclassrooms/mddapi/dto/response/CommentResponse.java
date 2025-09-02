package com.openclassrooms.mddapi.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response data for comment information
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

  private Long id;

  private String content;

  // private ThemeResponse theme;
  // private String themeName;

  // private UserResponse user;
  private String userName;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

}

