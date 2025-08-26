package com.openclassrooms.mddapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUserNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUniqueUserName {
    String message() default "Ce nom d'utilisateur est deja utilisé";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
