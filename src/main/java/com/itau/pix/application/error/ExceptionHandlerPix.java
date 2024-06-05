package com.itau.pix.application.error;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerPix {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    log.error("", ex);
    return ResponseEntity.unprocessableEntity().body(errors);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Map<String, String>> handleExceptions(HttpMessageNotReadableException ex) {
    Map<String, String> errors = new HashMap<>();
    errors.put("error", ex.getMessage());
    log.error("", ex);
    return ResponseEntity.unprocessableEntity().body(errors);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<Error> handleExceptions(MethodArgumentTypeMismatchException ex) {
    Error error = new Error(ex.getErrorCode(), ex.getMessage());
    log.error("{} ", error, ex);
    return ResponseEntity.unprocessableEntity().body(error);
  }

  @ExceptionHandler(UnexpectedTypeException.class)
  public ResponseEntity<Error> handleExceptions(UnexpectedTypeException ex) {
    Error error = new Error(ex.getCause().toString(), ex.getMessage());
    log.error("{} ", error, ex);
    return ResponseEntity.unprocessableEntity().body(error);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Error> handleExceptions(ConstraintViolationException ex) {
    Error error = new Error(ex.getCause().toString(), ex.getMessage());
    log.error("{} ", error, ex);
    return ResponseEntity.unprocessableEntity().body(error);
  }

  @ExceptionHandler(ViolacaoRegrasPixException.class)
  public ResponseEntity<Map<String, String>> handleExceptions(ViolacaoRegrasPixException ex) {
    Map<String, String> errors = new HashMap<>();
    errors.put("error", ex.getMessage());
    log.error("", ex);
    return ResponseEntity.unprocessableEntity().body(errors);
  }

  @ExceptionHandler(PixError.class)
  public ResponseEntity<Map<String, String>> handleExceptions(PixError ex) {
    Map<String, String> errors = new HashMap<>();
    errors.put("error", ex.getMessage());
    return ResponseEntity.unprocessableEntity().body(errors);
  }

  @ExceptionHandler(PixEntityNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleExceptions(PixEntityNotFoundException ex) {
    Map<String, String> errors = new HashMap<>();
    errors.put("error", ex.getMessage());
    log.error("", ex);
    return ResponseEntity.notFound().build();
  }
}
