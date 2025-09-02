package com.openclassrooms.mddapi.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response data for theme information
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThemeResponse {

  private Long id;

  private String name;

  private String description;

}
