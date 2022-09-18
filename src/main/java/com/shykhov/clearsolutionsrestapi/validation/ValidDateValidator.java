package com.shykhov.clearsolutionsrestapi.validation;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ValidDateValidator implements ConstraintValidator<ValidAge, LocalDate> {

    @Value("${user.age}")
    public int age;

    private static boolean isValidAge(LocalDate dateBirth, int age) {
        if (dateBirth != null) {
            return LocalDate.now().minus(age, ChronoUnit.YEARS).isAfter(dateBirth) ||
                    LocalDate.now().minus(age, ChronoUnit.YEARS).isEqual(dateBirth);
        } else return true;
    }

    @Override
    public void initialize(ValidAge constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate dateBirth, ConstraintValidatorContext constraintValidatorContext) {

        return isValidAge(dateBirth, age);
    }
}