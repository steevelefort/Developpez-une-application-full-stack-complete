package com.openclassrooms.mddapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Password validator.
 */
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

  /**
   * Check password is valid.
   * @param password password to check
   * @param context validation context
   * @return true if valid
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
