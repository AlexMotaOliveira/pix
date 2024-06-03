package com.itau.pix.domain.enums;

import com.itau.pix.application.validation.interfaces.ValidarChaveAleatoria;
import com.itau.pix.application.validation.interfaces.ValidarCnpj;
import com.itau.pix.application.validation.interfaces.ValidarCpf;
import com.itau.pix.application.validation.interfaces.ValidarEmail;
import com.itau.pix.application.validation.interfaces.ValidarTelefone;
import com.itau.pix.application.error.ViolacaoRegrasPixException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * Gets or Sets TipoChave
 */

@Getter
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
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

