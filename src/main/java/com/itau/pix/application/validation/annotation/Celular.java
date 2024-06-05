package com.itau.pix.application.validation.annotation;

import com.itau.pix.application.validation.TelefoneValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = TelefoneValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Celular {

  String message() default "Numero de celular invalido: valores definidos: +5511912345678 ou +55011912345678";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
