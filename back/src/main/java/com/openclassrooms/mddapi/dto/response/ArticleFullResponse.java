package com.openclassrooms.mddapi.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.openclassrooms.mddapi.model.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response data for full article with comments
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleFullResponse {

  private Long id;

  private String title;

  private String content;

  private String themeName;

  private String userName;

  private List<CommentResponse> comments;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

}
