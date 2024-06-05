package com.itau.pix.application.alteracao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.itau.pix.domain.enums.TipoConta;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record CorrentistaRequestDTO(
  @NotBlank String tipoConta,
  @Max(9999) int numeroAgencia,
  @Max(99999999) long numeroConta,
  @NotBlank String nomeCorrentista,
  String sobrenomeCorrentista) {

}
