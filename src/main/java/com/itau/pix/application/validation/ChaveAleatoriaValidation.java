package com.itau.pix.application.validation;

import com.itau.pix.application.validation.annotation.ChaveAleatoria;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ChaveAleatoriaValidation implements ConstraintValidator<ChaveAleatoria, String> {

  @Override
  public void initialize(ChaveAleatoria constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    String chaveAleatoria = value.isBlank() ? "" : value;
    return chaveAleatoria.matches("^\\w{1,36}$");
  }
}
