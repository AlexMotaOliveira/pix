package com.itau.pix.application.error;

public class ViolacaoRegrasPixException extends RuntimeException {

  public ViolacaoRegrasPixException() {
  }

  public ViolacaoRegrasPixException(String message) {
    super(message);
  }

}
