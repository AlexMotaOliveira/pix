package com.itau.pix.infrastructure.repository.inativacao;


import com.itau.pix.application.inativacao.dto.CadastroChavesPixDTO;

import java.util.UUID;

public interface InativacaoStorage {

  CadastroChavesPixDTO inativar(UUID uuid, String idCorrentista);


  boolean existeChaveAtiva(UUID uuid, String idCorrentista);
}
