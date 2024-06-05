package com.itau.pix.domain;

import com.itau.pix.domain.enums.TipoConta;
import com.itau.pix.domain.enums.TipoCorrentista;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;


public record Correntista(
  String idCorrentista,
  String tipoConta,
  int numeroAgencia,
  long numeroConta,
  String nomeCorrentista,
  String sobrenomeCorrentista,
  String tipoCorrentista) {

  public Correntista(@NotBlank String idCorrentista, @NotBlank String tipoConta, @Max(9999) int numeroAgencia,
                     @Max(99999999) long numeroConta, @NotBlank String nomeCorrentista, String sobrenomeCorrentista,
                     @NotBlank String tipoCorrentista) {
    this.idCorrentista = idCorrentista;
    this.tipoConta = TipoConta.fromString(tipoConta).name();
    this.numeroAgencia = numeroAgencia;
    this.numeroConta = numeroConta;
    this.nomeCorrentista = nomeCorrentista;
    this.sobrenomeCorrentista = sobrenomeCorrentista;
    this.tipoCorrentista = TipoCorrentista.fromString(tipoCorrentista).name();
  }
}
