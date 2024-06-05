package com.itau.pix.application.inclusao.impl;


import com.itau.pix.application.error.PixError;
import com.itau.pix.application.inclusao.PixInclusaoInterface;
import com.itau.pix.domain.enums.SituacaoChave;
import com.itau.pix.infrastructure.entity.CadastroChavesPix;
import com.itau.pix.infrastructure.entity.ChavePixEntity;
import com.itau.pix.infrastructure.entity.CorrentistaEntity;
import com.itau.pix.infrastructure.repository.inclusao.IncluirStorageService;
import com.itau.pix.model.IncluirRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class PixInclusaoService implements PixInclusaoInterface {

  private final IncluirStorageService incluirStorageService;

  @Override
  public UUID incluirChave(CadastroChavesPix correntistaEntity) {
    IncluirStorageService repository = incluirStorageService;
    UUID id = correntistaEntity.getChavePix().getId();
    if (repository.existsByIdAndSituacaoChave(id, SituacaoChave.ATIVA)) {
      throw new PixError("Chave já cadastrada");
    }

    int quantidadeChavesCorrentista = repository.
      countByIdCorrentistaAndNumeroAgenciaAndNumeroConta(correntistaEntity.getCorrentista().getIdCorrentista(),
        correntistaEntity.getCorrentista().getNumeroAgencia(), correntistaEntity.getCorrentista().getNumeroConta());

    if (quantidadeChavesCorrentista > correntistaEntity.getCorrentista().getTipoCorrentista().getValorMaximoChaves()) {
      throw new PixError("Cliente já possui limite de chaves cadastradas");
    }
    return repository.incluir(correntistaEntity);
  }

  public UUID incluirChave(CadastroChavesPix correntistaEntity, IncluirRequest incluirRequest) {
    IncluirStorageService repository = incluirStorageService;
    UUID id = correntistaEntity.getChavePix().getId();
    if (repository.existsByIdAndSituacaoChave(id, SituacaoChave.ATIVA)) {
      throw new PixError("Chave já cadastrada");
    }

    int quantidadeChavesCorrentista = repository.
      countByIdCorrentistaAndNumeroAgenciaAndNumeroConta(correntistaEntity.getCorrentista().getIdCorrentista(),
        correntistaEntity.getCorrentista().getNumeroAgencia(), correntistaEntity.getCorrentista().getNumeroConta());

    if (quantidadeChavesCorrentista > correntistaEntity.getCorrentista().getTipoCorrentista().getValorMaximoChaves()) {
      throw new PixError("Cliente já possui limite de chaves cadastradas");
    }
    return repository.incluir(incluirRequest);
  }

  public UUID incluirChave(CorrentistaEntity correntistaEntity, ChavePixEntity chavePix) {
    IncluirStorageService repository = incluirStorageService;
    UUID id = chavePix.getId();
    if (repository.existsByIdAndSituacaoChave(id, SituacaoChave.ATIVA)) {
      throw new PixError("Chave já cadastrada");
    }

    int quantidadeChavesCorrentista = repository.
      countByIdCorrentistaAndNumeroAgenciaAndNumeroConta(correntistaEntity.getIdCorrentista(),
        correntistaEntity.getNumeroAgencia(), correntistaEntity.getNumeroConta());

    if (quantidadeChavesCorrentista > correntistaEntity.getTipoCorrentista().getValorMaximoChaves()) {
      throw new PixError("Cliente já possui limite de chaves cadastradas");
    }
    return repository.incluir(correntistaEntity, chavePix);
  }
}
