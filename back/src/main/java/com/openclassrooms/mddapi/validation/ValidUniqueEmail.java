package com.openclassrooms.mddapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUniqueEmail {
    String message() default "Cette adresse email est deja utilisée";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
