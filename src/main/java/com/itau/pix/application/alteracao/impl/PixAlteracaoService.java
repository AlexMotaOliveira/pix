package com.itau.pix.application.alteracao.impl;


import com.itau.pix.application.alteracao.Correntista;
import com.itau.pix.application.alteracao.PixAlteracaoInterface;
import com.itau.pix.application.error.PixEntityNotFoundException;
import com.itau.pix.application.error.PixError;
import com.itau.pix.domain.enums.SituacaoChave;
import com.itau.pix.infrastructure.entity.CadastroChavesPix;
import com.itau.pix.infrastructure.entity.ChavePixEntity;
import com.itau.pix.infrastructure.entity.CorrentistaEntity;
import com.itau.pix.infrastructure.repository.alteracao.AlteracaoStorage;
import com.itau.pix.infrastructure.repository.alteracao.AlteracaoStorageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class PixAlteracaoService implements PixAlteracaoInterface {

  private final AlteracaoStorageService alteracaoStorage;

  @Override
  public CadastroChavesPix alterarChave(Correntista correntista, UUID id) {
    try {

      CadastroChavesPix cadastroChavesPixEntity = getReferenceById(id);
      ChavePixEntity chavePix = cadastroChavesPixEntity.getChavePix();
      if (chavePix.getSituacaoChave() == SituacaoChave.ATIVA) {
        CorrentistaEntity correntistaEntity = cadastroChavesPixEntity.getCorrentista();
        correntistaEntity.setTipoConta(correntista.getTipoConta());
        correntistaEntity.setNumeroConta(correntista.getNumeroConta());
        correntistaEntity.setNumeroAgencia(correntistaEntity.getNumeroAgencia());
        correntistaEntity.setNomeCorrentista(correntista.getNomeCorrentista());
        Optional.ofNullable(correntista.getSobrenomeCorrentista())
          .ifPresent(correntistaEntity::setSobrenomeCorrentista);
        return alteracaoStorage.alterar(cadastroChavesPixEntity);
      }
      throw new PixError("chave desativada, não é possivel fazer atualização");
    } catch (EntityNotFoundException e) {
      throw new PixEntityNotFoundException("chave não localizada");
    }
  }


  private CadastroChavesPix getReferenceById(UUID id) {
    return alteracaoStorage.buscarPorChavePix(id);
  }

}
