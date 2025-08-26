package com.openclassrooms.mddapi.dto.request;

import com.openclassrooms.mddapi.validation.ValidPassword;
import com.openclassrooms.mddapi.validation.ValidUniqueEmail;
import com.openclassrooms.mddapi.validation.ValidUniqueUserName;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterRequest {
    
  @NotBlank(message = "Le nom d'utilisateur est obligatoire")
  @Size(max = 100, message = "Le nom d'utilisateur doit avoir moins de 100 caractères")
  @ValidUniqueUserName
  private String userName;

  @NotBlank(message = "L'adresse email est obligatoire")
  @Size(max = 255, message = "L'adresse email doit avoir moins de 255 caractères")
  @ValidUniqueEmail
  private String email;

  @NotBlank(message = "Le mot de passe est obligatoire")
  @ValidPassword
  private String password;

}
