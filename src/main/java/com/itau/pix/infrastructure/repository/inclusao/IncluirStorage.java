package com.itau.pix.infrastructure.repository.inclusao;


import com.itau.pix.domain.enums.SituacaoChave;
import com.itau.pix.infrastructure.entity.CadastroChavesPix;

import java.util.UUID;

public interface IncluirStorage {

  UUID incluir(CadastroChavesPix correntista);

  boolean existsByIdAndSituacaoChave(UUID id, SituacaoChave situacaoChave);

  int countByIdCorrentistaAndNumeroAgenciaAndNumeroConta(Long idCorrentista, int numeroAgencia, long numeroConta);
}
