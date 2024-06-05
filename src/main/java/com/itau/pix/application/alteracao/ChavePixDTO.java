package com.itau.pix.application.alteracao;

import com.itau.pix.domain.enums.TipoChave;

import java.time.OffsetDateTime;
import java.util.UUID;


public record ChavePixDTO(
  UUID uuid,
  TipoChave tipoChave,
  String valorChave,
  OffsetDateTime dataHoraInclusaoDaChave
) {

}
