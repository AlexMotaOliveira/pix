package com.itau.pix.application.operacoes.consulta.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.itau.pix.domain.enums.TipoChave;

import java.sql.Timestamp;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record ChavePixDTO(
  UUID uuid,
  TipoChave tipoChave,
  String valorChave,
  Timestamp dataHoraInclusaoDaChave,
  Timestamp dataHoraInativacaoDaChave) {

}
