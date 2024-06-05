package com.itau.pix.application.inativacao;

import com.itau.pix.application.inativacao.dto.CadastroChavesPixDTO;

import java.util.UUID;

public interface PixInativacaoInterface {

  CadastroChavesPixDTO inativar(UUID id, String IdCorrentista);


}
