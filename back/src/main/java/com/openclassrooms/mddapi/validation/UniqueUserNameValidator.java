package com.openclassrooms.mddapi.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.openclassrooms.mddapi.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Username unique validator.
 */
public class UniqueUserNameValidator implements ConstraintValidator<ValidUniqueUserName, String> {

  private UserRepository userRepository;

  public UniqueUserNameValidator(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Check username is unique.
   * @param userName username to check
   * @param context validation context
   * @return true if unique
   */
  @Override
  public boolean isValid(String userName, ConstraintValidatorContext context) {
    if (userName == null)
      return false;

    return !userRepository.existsByUserName(userName);
  }
}
