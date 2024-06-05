package com.itau.pix.infrastructure.repository.inclusao;


import com.itau.pix.domain.enums.SituacaoChave;

import java.util.UUID;

public interface IncluirStorage {

  UUID incluir(com.itau.pix.domain.CadastroChavesPix cadastroChavesPix);

  boolean existsByIdAndSituacaoChave(UUID id, SituacaoChave situacaoChave);

  int countByIdCorrentistaAndNumeroAgenciaAndNumeroConta(Long idCorrentista, int numeroAgencia, long numeroConta);
}
