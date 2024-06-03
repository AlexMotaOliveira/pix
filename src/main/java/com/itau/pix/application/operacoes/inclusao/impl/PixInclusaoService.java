package com.itau.pix.application.operacoes.inclusao.impl;


import com.itau.pix.application.error.PixError;
import com.itau.pix.application.operacoes.inclusao.PixInclusaoInterface;
import com.itau.pix.domain.CadastroChavesPix;
import com.itau.pix.domain.Correntista;
import com.itau.pix.domain.enums.SituacaoChave;
import com.itau.pix.domain.enums.TipoCorrentista;
import com.itau.pix.infrastructure.repository.inclusao.IncluirStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class PixInclusaoService implements PixInclusaoInterface {

  private final IncluirStorageService incluirStorageService;

  @Override
  public UUID incluirChave(CadastroChavesPix cadastroChavesPix) {
    IncluirStorageService storage = incluirStorageService;
    UUID id = UUID.nameUUIDFromBytes(cadastroChavesPix.chavePix().valorChave().getBytes(StandardCharsets.UTF_8));
    if (storage.existsByIdAndSituacaoChave(id, SituacaoChave.ATIVA)) {
      String message = "Chave já cadastrada: ";
      log.error("{} {}", message, id);
      throw new PixError(message);
    }

    Correntista correntista = cadastroChavesPix.correntista();
    int quantidadeChavesCorrentista = storage.
      countByIdCorrentistaAndNumeroAgenciaAndNumeroConta(correntista.idCorrentista(),
        correntista.numeroAgencia(), correntista.numeroConta());

    TipoCorrentista tipoCorrentista = TipoCorrentista.fromString(correntista.tipoCorrentista());
    if (quantidadeChavesCorrentista > tipoCorrentista.getValorMaximoChaves()) {
      String message = "Cliente já possui limite de chaves cadastradas";
      log.error("{} {}", message, correntista.idCorrentista());
      throw new PixError(message);
    }
    return storage.incluir(cadastroChavesPix);
  }

}
