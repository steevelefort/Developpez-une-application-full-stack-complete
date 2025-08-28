package com.openclassrooms.mddapi.dto.request;

import com.openclassrooms.mddapi.validation.ValidPassword;
import com.openclassrooms.mddapi.validation.ValidThemeExists;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

  @NotNull(message = "Le thème est obligatoire")
  @ValidThemeExists
  private Long themeId;


}
