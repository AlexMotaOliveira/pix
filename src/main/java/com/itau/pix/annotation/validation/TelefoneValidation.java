package com.itau.pix.annotation.validation;


import com.itau.pix.annotation.Celular;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TelefoneValidation implements ConstraintValidator<Celular, String> {

  @Override
  public void initialize(Celular constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    String chaveAleatoria = value.isBlank() ? "" : value;
    return chaveAleatoria.matches("^\\+[0-9]{2}[0-9]{2,3}9[\\d]{8}$");

  }
}
