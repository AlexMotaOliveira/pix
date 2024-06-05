package com.itau.pix.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;


public record Correntista(
  @NotBlank String idCorrentista,
  @NotBlank String tipoConta,
  @Max(9999) int numeroAgencia,
  @Max(99999999) long numeroConta,
  @NotBlank String nomeCorrentista,
  String sobrenomeCorrentista,
  @NotBlank String tipoCorrentista) {
}
