package com.openclassrooms.mddapi.validation;

import com.openclassrooms.mddapi.repository.ThemeRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Theme exists validator.
 */
public class ThemeExistsValidator implements ConstraintValidator<ValidThemeExists, Long> {

  private ThemeRepository themeRepository;

  public ThemeExistsValidator(ThemeRepository themeRepository) {
    this.themeRepository = themeRepository;
  }

  /**
   * Check theme exists.
   * @param themeId theme id to check
   * @param context validation context
   * @return true if exists
   */
  @Override
  public boolean isValid(Long themeId, ConstraintValidatorContext context) {
    return themeRepository.existsById(themeId);
  }
}
