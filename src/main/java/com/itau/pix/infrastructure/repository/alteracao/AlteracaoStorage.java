package com.itau.pix.infrastructure.repository.alteracao;


import com.itau.pix.application.alteracao.dto.CadastroChavesPixDTO;
import com.itau.pix.application.alteracao.dto.CorrentistaRequestDTO;
import com.itau.pix.domain.enums.SituacaoChave;

import java.util.UUID;

public interface AlteracaoStorage {


  CadastroChavesPixDTO alterar(CorrentistaRequestDTO correntista, String idCorrentista, UUID idChavePix);

  boolean existsByIdAndSituacaoChave(UUID id, String idCorrentista, SituacaoChave situacaoChave);
}
