package com.itau.pix.application.validation.annotation;

import com.itau.pix.application.validation.PontoNoCpfCnpjValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = PontoNoCpfCnpjValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ContemPonto {

  String message() default "cpf/cnpj deve conter somente numeros";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
