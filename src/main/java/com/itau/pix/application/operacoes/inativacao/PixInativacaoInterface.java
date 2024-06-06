package com.itau.pix.application.operacoes.inativacao;

import com.itau.pix.application.operacoes.inativacao.dto.CadastroChavesPixDTO;

import java.util.UUID;

public interface PixInativacaoInterface {

  CadastroChavesPixDTO inativar(UUID id, String idCorrentista);


}
