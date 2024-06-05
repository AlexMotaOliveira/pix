package com.itau.pix.application.inativacao;

import java.util.UUID;

public interface PixInativacaoInterface {

  CadastroChavesPixDTO inativar(UUID id, String IdCorrentista);


}
