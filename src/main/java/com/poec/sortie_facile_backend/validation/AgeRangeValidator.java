package com.poec.sortie_facile_backend.validation;

import com.poec.sortie_facile_backend.annotation.ValidAgeRange;
import com.poec.sortie_facile_backend.domain.activity.dto.SaveActivityDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AgeRangeValidator implements ConstraintValidator<ValidAgeRange, SaveActivityDTO> {

    @Override
    public boolean isValid(SaveActivityDTO activity, ConstraintValidatorContext context) {
        return activity.ageMax() > activity.ageMin();
    }
}