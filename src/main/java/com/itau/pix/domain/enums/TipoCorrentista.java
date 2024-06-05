package com.itau.pix.domain.enums;

import com.itau.pix.application.error.ViolacaoRegrasPixException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum TipoCorrentista {

  PF("Pessoa Fisica", 5),
  PJ("Pessoa Juridica", 20);

  private final String tipo;
  private final int valorMaximoChaves;

  public static TipoCorrentista fromString(String value) {
    for (TipoCorrentista tipoCorrentista : values()) {
      if (tipoCorrentista.name().equalsIgnoreCase(value)) {
        return tipoCorrentista;
      }
    }
    throw new ViolacaoRegrasPixException("Tipo Cliente invalido, valores definidos: " + Arrays.toString(values()));
  }
}
