package com.openclassrooms.mddapi.dto.response;

import java.time.LocalDateTime;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response data for article information
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {

  private Long id;

  private String title;

  private String content;

  // private ThemeResponse theme;
  private String themeName;

  // private UserResponse user;
  private String userName;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

}

