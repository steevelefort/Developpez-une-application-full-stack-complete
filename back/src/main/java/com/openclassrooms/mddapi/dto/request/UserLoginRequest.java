package com.openclassrooms.mddapi.dto.request;

import com.openclassrooms.mddapi.validation.ValidPassword;
import com.openclassrooms.mddapi.validation.ValidUniqueEmail;
import com.openclassrooms.mddapi.validation.ValidUniqueUserName;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Request data for user login
 */
@Data
public class UserLoginRequest {
    
  @NotBlank(message = "Le nom d'utilisateur ou le mot de passe est obligatoire")
  private String identifier;

  @NotBlank(message = "Le mot de passe est obligatoire")
  private String password;

}
