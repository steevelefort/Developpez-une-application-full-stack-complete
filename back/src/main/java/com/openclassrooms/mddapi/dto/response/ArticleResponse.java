package com.openclassrooms.mddapi.dto.response;

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

  @NotBlank
  @Size(max=100)
  private String name;

  @Size(max=2000)
  private String description;

}

