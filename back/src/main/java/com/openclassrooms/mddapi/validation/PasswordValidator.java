package com.openclassrooms.mddapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

  /**
   * Check a password validity if it’s set
   * Be sure to use the @NoBlank validator if needed !
   */
  @Override
  public boolean isValid(String password, ConstraintValidatorContext context) {
    if (password == null || password.length()==0)
      return true;

    return password.length() >= 8 &&
        password.matches(".*[a-z].*") &&
        password.matches(".*[A-Z].*") &&
        password.matches(".*\\d.*") &&
        password.matches(".*[^a-zA-Z\\d].*");
  }
}
