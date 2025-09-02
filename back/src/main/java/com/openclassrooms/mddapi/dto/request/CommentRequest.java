package com.openclassrooms.mddapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request data for creating a comment
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {

  @NotBlank(message = "Veuillez renseigner le contenu du commentaire")
  private String content;

}
