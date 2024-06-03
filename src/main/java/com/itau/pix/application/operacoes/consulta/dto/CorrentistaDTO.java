package com.itau.pix.application.operacoes.consulta.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.itau.pix.domain.enums.TipoConta;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record CorrentistaDTO(
  String idCorrentista,
  TipoConta tipoConta,
  int numeroAgencia,
  long numeroConta,
  String nomeCorrentista,
  String sobrenomeCorrentista) {

}
