package com.openclassrooms.mddapi.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.openclassrooms.mddapi.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<ValidUniqueEmail, String> {

  private UserRepository userRepository;

  public UniqueEmailValidator(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {
    if (email == null)
      return false;

    return !userRepository.existsByEmail(email);
  }
}
