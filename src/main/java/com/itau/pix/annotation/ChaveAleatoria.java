package com.itau.pix.annotation;

import com.itau.pix.annotation.validation.ChaveAleatoriaValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = ChaveAleatoriaValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ChaveAleatoria {

  String message() default "chave aleatoria deve conter no maximo 36 caracteres alfanumericos";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
