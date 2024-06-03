package com.itau.pix.application.operacoes.alteracao.impl;


import com.itau.pix.application.error.PixError;
import com.itau.pix.application.operacoes.alteracao.dto.CadastroChavesPixDTO;
import com.itau.pix.application.operacoes.alteracao.dto.CorrentistaRequestDTO;
import com.itau.pix.domain.enums.SituacaoChave;
import com.itau.pix.infrastructure.repository.alteracao.AlteracaoStorage;
import com.itau.pix.infrastructure.repository.alteracao.AlteracaoStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class PixAlteracaoService {

  private final AlteracaoStorageService alteracaoStorage;

  public CadastroChavesPixDTO alterarChave(CorrentistaRequestDTO correntista, UUID idChavePix, String idCorrentista) {
    AlteracaoStorage storage = alteracaoStorage;
    if (storage.existsByIdAndSituacaoChave(idChavePix, idCorrentista, SituacaoChave.ATIVA)) {
      return alteracaoStorage.alterar(correntista, idCorrentista, idChavePix);
    }
    throw new PixError("chave desativada, não é possivel fazer atualização");
  }

}
