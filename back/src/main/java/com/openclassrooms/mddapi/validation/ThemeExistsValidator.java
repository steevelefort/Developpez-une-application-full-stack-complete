package com.openclassrooms.mddapi.validation;

import com.openclassrooms.mddapi.repository.ThemeRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ThemeExistsValidator implements ConstraintValidator<ValidThemeExists, Long> {

  private ThemeRepository themeRepository;

  public ThemeExistsValidator(ThemeRepository themeRepository) {
    this.themeRepository = themeRepository;
  }

  /**
   * Check if the specified theme exists 
   */
  @Override
  public boolean isValid(Long themeId, ConstraintValidatorContext context) {
    return themeRepository.existsById(themeId);
  }
}
