package com.itau.pix.error;

public class ViolacaoRegrasPixException extends RuntimeException {

  public ViolacaoRegrasPixException() {
  }

  public ViolacaoRegrasPixException(String message) {
    super(message);
  }

  public ViolacaoRegrasPixException(String message, Throwable cause) {
    super(message, cause);
  }
}
