package com.itau.pix.infrastructure.repository.inclusao;


import com.itau.pix.domain.enums.SituacaoChave;

import java.util.UUID;

public interface IncluirStorage {

  int countByIdCorrentistaAndNumeroAgenciaAndNumeroConta(String idCorrentista, int numeroAgencia, long numeroConta);

  UUID incluir(com.itau.pix.domain.CadastroChavesPix cadastroChavesPix);

  boolean existsByIdAndSituacaoChave(UUID id, SituacaoChave situacaoChave);

}
