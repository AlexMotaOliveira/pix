package com.itau.pix.enums;

import com.itau.pix.annotation.validation.interfaces.ValidarChaveAleatoria;
import com.itau.pix.annotation.validation.interfaces.ValidarCnpj;
import com.itau.pix.annotation.validation.interfaces.ValidarCpf;
import com.itau.pix.annotation.validation.interfaces.ValidarEmail;
import com.itau.pix.annotation.validation.interfaces.ValidarTelefone;
import com.itau.pix.error.ViolacaoRegrasPixException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * Gets or Sets TipoChave
 */

@Getter
@RequiredArgsConstructor
public enum TipoChave {

  celular("celular", ValidarTelefone.class),

  email("email", ValidarEmail.class),

  cpf("cpf", ValidarCpf.class),

  cnpj("cnpj", ValidarCnpj.class),

  aleatorio("aleatorio", ValidarChaveAleatoria.class);

  private final String value;
  private final Class<?> group;

  public static TipoChave fromString(String value) {
    for (TipoChave tipoChave : values()) {
      if (tipoChave.name().equalsIgnoreCase(value)) {
        return tipoChave;
      }
    }
    throw new ViolacaoRegrasPixException("Tipo Chave invalido, valores definidos: " + Arrays.toString(values()));
  }

}

