package com.itau.pix.error;

public class PixEntityNotFoundException extends RuntimeException {

  public PixEntityNotFoundException(String message) {
    super(message);
  }

  public PixEntityNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
