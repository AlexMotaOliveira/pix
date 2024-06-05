package com.itau.pix.infrastructure.repository.inativacao;


import com.itau.pix.application.inativacao.CadastroChavesPixDTO;
import com.itau.pix.infrastructure.entity.CadastroChavesPixEntity;

import java.util.UUID;

public interface InativacaoStorage {

  CadastroChavesPixDTO inativar(UUID uuid, String idCorrentista);


  boolean existeChaveAtiva(UUID uuid, String idCorrentista);
}
