package com.poec.sortie_facile_backend.annotation;

import com.poec.sortie_facile_backend.validation.AgeRangeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AgeRangeValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAgeRange {
    String message() default "ageMax doit être strictement supérieur à ageMin";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
