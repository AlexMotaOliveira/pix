package com.itau.pix.application.inativacao.impl;


import com.itau.pix.application.error.PixError;
import com.itau.pix.application.inativacao.PixInativacaoInterface;
import com.itau.pix.domain.enums.SituacaoChave;
import com.itau.pix.infrastructure.entity.CadastroChavesPix;
import com.itau.pix.infrastructure.entity.ChavePixEntity;
import com.itau.pix.infrastructure.repository.inativacao.InativacaoStorageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class PixInativacaoService implements PixInativacaoInterface {

  private final InativacaoStorageService inativacaoStorageService;

  @Override
  public CadastroChavesPix inativar(UUID id) {
    try {
      CadastroChavesPix cadastroChavesPix = getReferenceById(id);
      ChavePixEntity chavePix = cadastroChavesPix.getChavePix();
      if (chavePix.getSituacaoChave() == SituacaoChave.ATIVA) {
        OffsetDateTime from = OffsetDateTime.from(new Date().toInstant());
        chavePix.setDataHoraInativacaoDaChave(from);

        return inativacaoStorageService.inativar(cadastroChavesPix);
      }
      throw new PixError("Chave já estava desativada");
    } catch (EntityNotFoundException e) {
      throw new PixError("chave não localizada");
    }
  }


  private CadastroChavesPix getReferenceById(UUID id) {
    return inativacaoStorageService.buscarPorChavePix(id);
  }

}
