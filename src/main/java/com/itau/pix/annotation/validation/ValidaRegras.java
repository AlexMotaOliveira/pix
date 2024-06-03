package com.itau.pix.annotation.validation;

import com.itau.pix.error.ViolacaoRegrasPixException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class ValidaRegras<T> {

  private final Validator validator;

  public void validar(T t) {
    Set<ConstraintViolation<T>> violations = validator.validate(t);

    if (!violations.isEmpty()) {
      StringBuilder sb = new StringBuilder();
      for (ConstraintViolation<T> constraintViolation : violations) {
        sb.append(constraintViolation.getPropertyPath());
        sb.append(" ");
        sb.append(constraintViolation.getMessage());
      }
      throw new ViolacaoRegrasPixException(sb.toString());
    }
  }
}
