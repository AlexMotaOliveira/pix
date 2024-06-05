package com.itau.pix.application.alteracao;

import com.itau.pix.infrastructure.entity.CadastroChavesPix;

import java.util.UUID;

public interface PixAlteracaoInterface {

  CadastroChavesPix alterarChave(Correntista correntista, UUID uuid);

}
