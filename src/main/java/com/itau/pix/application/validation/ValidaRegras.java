package com.itau.pix.application.validation;

import com.itau.pix.application.error.ViolacaoRegrasPixException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class ValidaRegras<T> {

  private final Validator validator;

  public void validar(T t) {
    Set<ConstraintViolation<T>> violations = validator.validate(t);

    if (!violations.isEmpty()) {
      StringBuilder sb = new StringBuilder();
      for (ConstraintViolation<T> constraintViolation : violations) {
        sb.append(constraintViolation.getPropertyPath());
        sb.append(":");
        sb.append(constraintViolation.getMessage());
      }
      throw new ViolacaoRegrasPixException(sb.toString());
    }
  }
}
