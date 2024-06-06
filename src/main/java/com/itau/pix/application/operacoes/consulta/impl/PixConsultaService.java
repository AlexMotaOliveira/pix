package com.itau.pix.application.operacoes.consulta.impl;


import com.itau.pix.application.error.PixError;
import com.itau.pix.application.operacoes.consulta.PixConsutaInterface;
import com.itau.pix.application.operacoes.consulta.dto.CadastroChavesPixDTO;
import com.itau.pix.domain.enums.TipoChave;
import com.itau.pix.infrastructure.repository.consulta.ConsultaStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class PixConsultaService implements PixConsutaInterface {

  private final ConsultaStorageService consultaStorageService;

  @Override
  public Page<CadastroChavesPixDTO> consultar(UUID id, String nomeCorrentista,
                                              String tipoChave, String agencia, String conta,
                                              String paginacao, String quantidade) {
    ConsultaStorageService storageService = consultaStorageService;
    if (validarFiltroCombinadoComId(id, tipoChave, agencia, conta)) {
      return storageService.consultarId(id);
    }

    validarFiltroCombinadoComAgenciaConta(agencia, conta);
    validarFiltroCombinadoComTipoChave(tipoChave);

    storageService.consultarFiltroCombinado(nomeCorrentista, tipoChave, agencia, conta, quantidade, paginacao);
    return null;
  }


//  @Override
//  public UUID incluirChave(CadastroChavesPix cadastroChavesPix) {
//    IncluirStorageService storage = incluirStorageService;
//    UUID id = UUID.nameUUIDFromBytes(cadastroChavesPix.chavePix().valorChave().getBytes(StandardCharsets.UTF_8));
//    if (storage.existsByIdAndSituacaoChave(id, SituacaoChave.ATIVA)) {
//      String message = "Chave já cadastrada: ";
//      log.error("{} {}", message, id);
//      throw new PixError(message);
//    }
//
//    Correntista correntista = cadastroChavesPix.correntista();
//    int quantidadeChavesCorrentista = storage.
//      countByIdCorrentistaAndNumeroAgenciaAndNumeroConta(correntista.idCorrentista(),
//        correntista.numeroAgencia(), correntista.numeroConta());
//
//    TipoCorrentista tipoCorrentista = TipoCorrentista.fromString(correntista.tipoCorrentista());
//    if (quantidadeChavesCorrentista > tipoCorrentista.getValorMaximoChaves()) {
//      String message = "Cliente já possui limite de chaves cadastradas";
//      log.error("{} {}", message, correntista.idCorrentista());
//      throw new PixError(message);
//    }
//    return storage.incluir(cadastroChavesPix);
//  }

  private static boolean validarFiltroCombinadoComId(UUID id, String tipoChave, String agencia, String conta) {
    if (id != null) {
      if ((tipoChave != null && !tipoChave.isBlank()) ||
        (agencia != null && !agencia.isBlank()) ||
        (conta != null && !conta.isBlank())) {

        throw new PixError("Id não pode ser informado em consultas combinadas");
      }
      return true;
    }
    return false;
  }


  private static void validarFiltroCombinadoComAgenciaConta(String agencia, String conta) {
    if (agencia != null && !agencia.isBlank() || (conta != null && conta.isBlank())) {
      throw new PixError("A combinação de agencia e conta devem ser informadas");
    }


    if ((agencia != null && !agencia.matches("^\\d{1,4}$")) ||
      (conta != null && !conta.matches("^\\d{1,8}$"))) {
      throw new PixError("agência e conta devem conter somente digitos");
    }
  }


  private static void validarFiltroCombinadoComTipoChave(String tipoChave) {
    if (tipoChave != null && !tipoChave.isBlank()) {
      TipoChave.fromString(tipoChave);
    }
  }

}
