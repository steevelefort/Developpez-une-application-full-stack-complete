package com.openclassrooms.mddapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ThemeExistsValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidThemeExists {
    String message() default "Le thème sélectionné n'existe pas";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
