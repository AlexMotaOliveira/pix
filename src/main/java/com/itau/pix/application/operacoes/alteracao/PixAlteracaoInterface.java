package com.itau.pix.application.operacoes.alteracao;

import com.itau.pix.application.operacoes.alteracao.dto.CorrentistaRequestDTO;
import com.itau.pix.infrastructure.entity.CadastroChavesPixEntity;

import java.util.UUID;

public interface PixAlteracaoInterface {

  CadastroChavesPixEntity alterarChave(CorrentistaRequestDTO correntista, UUID uuid);

}
