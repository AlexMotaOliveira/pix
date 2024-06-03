package com.itau.pix.annotation.validation;


import com.itau.pix.annotation.ContemPonto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PontoNoCpfCnpjValidation implements ConstraintValidator<ContemPonto, String> {

  @Override
  public void initialize(ContemPonto constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    String chaveAleatoria = value.isBlank() ? "" : value;
    return chaveAleatoria.matches("^\\d{11,14}$");

  }
}
