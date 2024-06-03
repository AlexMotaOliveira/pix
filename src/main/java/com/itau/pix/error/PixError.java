package com.itau.pix.error;

public class PixError extends RuntimeException {

  public PixError(String message) {
    super(message);
  }

  public PixError(String message, Throwable cause) {
    super(message, cause);
  }
}
