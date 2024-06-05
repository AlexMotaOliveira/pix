package com.itau.pix.application.consulta.dto;

import com.itau.pix.domain.enums.TipoChave;

import java.sql.Timestamp;
import java.util.UUID;


public record ChavePixDTO(
  UUID uuid,
  TipoChave tipoChave,
  String valorChave,
  Timestamp dataHoraInclusaoDaChave
) {

}
