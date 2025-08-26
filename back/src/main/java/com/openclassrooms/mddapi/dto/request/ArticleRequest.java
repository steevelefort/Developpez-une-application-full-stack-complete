package com.openclassrooms.mddapi.dto.request;

import com.openclassrooms.mddapi.validation.ValidPassword;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest {

  @NotBlank(message = "Veuillez renseigner le titre de l'article")
  @Size(max = 100, message = "Le titre ne doit pas dépasser 100 caractères")
  private String title;

  @NotBlank(message = "Veuillez renseigner le contenu de l'article")
  private String content;

  @NotBlank(message = "Le mot de passe est obligatoire")
  @ValidPassword
  private Long themeId;

}
