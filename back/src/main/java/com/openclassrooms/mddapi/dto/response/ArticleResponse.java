package com.openclassrooms.mddapi.dto.response;

import java.time.LocalDateTime;

import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.model.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {

  private Long id;

  private String title;

  private String content;

  private Theme theme;

  private User user;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

}

