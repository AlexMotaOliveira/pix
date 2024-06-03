package com.itau.pix.enums;

import com.itau.pix.error.ViolacaoRegrasPixException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum TipoCliente {

  PF("Pessoa Fisica", 5),
  PJ("Pessoa Juridica", 20);

  private final String tipo;
  private final int valorMaximoChaves;

  public static TipoCliente fromString(String value) {
    for (TipoCliente tipoCliente : values()) {
      if (tipoCliente.name().equalsIgnoreCase(value)) {
        return tipoCliente;
      }
    }
    throw new ViolacaoRegrasPixException("Tipo Cliente invalido, valores definidos: " + Arrays.toString(values()));
  }
}
