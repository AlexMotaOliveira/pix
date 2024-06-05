package com.itau.pix.application.error;

public class PixEntityNotFoundException extends RuntimeException {

  public PixEntityNotFoundException(String message) {
    super(message);
  }

}
