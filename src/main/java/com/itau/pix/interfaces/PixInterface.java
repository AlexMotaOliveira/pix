package com.itau.pix.interfaces;

import com.itau.pix.entity.Pix;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface PixInterface {

  UUID incluirChave(Pix pix);

  boolean validarChaveExistente(Pix pix);

  boolean validarLimiteChaves(Pix pix);

  Pix inativar(UUID id);

  Pix alterarChave(Pix pixEntity);

  List<Pix> listarChaves(UUID id,
                         String tipoChave, String agenciaConta,
                         String nomeCorrentista,
                         OffsetDateTime dataCriacaoChave,
                         OffsetDateTime dataInativacaoChave);

  Pix buscarId(UUID uuid);
}
