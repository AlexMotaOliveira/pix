package com.itau.pix.application.operacoes.inativacao.impl;


import com.itau.pix.application.error.PixError;
import com.itau.pix.application.operacoes.inativacao.PixInativacaoInterface;
import com.itau.pix.application.operacoes.inativacao.dto.CadastroChavesPixDTO;
import com.itau.pix.infrastructure.repository.inativacao.InativacaoStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class PixInativacaoService implements PixInativacaoInterface {

  private final InativacaoStorageService inativacaoStorageService;

  @Override
  public CadastroChavesPixDTO inativar(UUID id, String idCorrentista) {
    //TODO validar se a chave pertence ao IdCorrentista
    if (existeChaveAtiva(id, idCorrentista)) {
      return inativacaoStorageService.inativar(id, idCorrentista);
    }
    String message = "Chave já estava desativada";
    log.error("{} {}", message, id);
    throw new PixError(message);
  }


  private boolean existeChaveAtiva(UUID id, String idCorrentista) {
    return inativacaoStorageService.existeChaveAtiva(id, idCorrentista);
  }

}
