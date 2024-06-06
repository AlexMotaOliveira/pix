package com.itau.pix.application.operacoes.alteracao.dto;

import com.itau.pix.domain.enums.TipoChave;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;


public record ChavePixDTO(
  UUID uuid,
  TipoChave tipoChave,
  String valorChave,
  Timestamp dataHoraInclusaoDaChave
) {

}
