package com.itau.pix.domain.enums;

import com.itau.pix.application.error.ViolacaoRegrasPixException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public enum TipoConta {

  CORRENTE("corrente"),

  POUPANCA("poupanca");

  private final String value;

  public static TipoConta fromString(String value) {
    for (TipoConta tipoConta : values()) {
      if (tipoConta.name().equalsIgnoreCase(value)) {
        return tipoConta;
      }
    }
    throw new ViolacaoRegrasPixException("Tipo Chave invalido, valores definidos: " + Arrays.toString(values()));
  }
}

