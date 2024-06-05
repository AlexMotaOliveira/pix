package com.itau.pix.domain;

import com.itau.pix.application.validation.ValorChaveDomainGroupSequenceProvider;
import com.itau.pix.application.validation.annotation.Celular;
import com.itau.pix.application.validation.annotation.ChaveAleatoria;
import com.itau.pix.application.validation.annotation.ContemPonto;
import com.itau.pix.application.validation.interfaces.ValidarChaveAleatoria;
import com.itau.pix.application.validation.interfaces.ValidarCnpj;
import com.itau.pix.application.validation.interfaces.ValidarCpf;
import com.itau.pix.application.validation.interfaces.ValidarEmail;
import com.itau.pix.application.validation.interfaces.ValidarTelefone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

@GroupSequenceProvider(ValorChaveDomainGroupSequenceProvider.class)
public record ChavePix(
  @NotBlank String tipoChave,

  @NotBlank
  @Size(max = 77)
  @CPF(groups = {ValidarCpf.class})
  @CNPJ(groups = {ValidarCnpj.class})
  @ContemPonto(groups = {ValidarCpf.class, ValidarCnpj.class})
  @Email(groups = ValidarEmail.class)
  @Celular(groups = ValidarTelefone.class)
  @ChaveAleatoria(groups = ValidarChaveAleatoria.class)
  String valorChave) {

}
