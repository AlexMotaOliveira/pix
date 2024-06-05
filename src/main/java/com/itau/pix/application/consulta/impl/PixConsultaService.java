package com.itau.pix.application.consulta.impl;


import com.itau.pix.application.consulta.PixConsutaInterface;
import com.itau.pix.application.error.PixError;
import com.itau.pix.infrastructure.repository.consulta.ConsultaStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class PixConsultaService implements PixConsutaInterface {

  private final ConsultaStorageService consultaStorageService;


  @Override
  public void consultar(UUID id, String idCorrentista, String tipoChave, String valorChave, String agencia, String conta) {
    ConsultaStorageService storageService = consultaStorageService;
    if (validarFiltroCombinadoComId(id, tipoChave, agencia, conta)) {
      storageService.consultarId(id);
      //TODO montar o objeto de retorno
    }

    validarFiltroCombinadoComAgenciaConta(agencia, conta);
    validarFiltroCombinadoComDatas(agencia, conta);

    storageService.consultarFiltroCombinado(idCorrentista, tipoChave, valorChave, agencia, conta);

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
      if (!tipoChave.isBlank() || !agencia.isBlank() || !conta.isBlank()) {
        throw new PixError("Id não pode ser informado em consultas combinadas");
      }
      return true;
    }
    return false;
  }

  private static void validarFiltroCombinadoComAgenciaConta(String agencia, String conta) {
    if (agencia.isBlank() || !conta.isBlank()) {
      throw new PixError("A combinação de agencia e conta devem ser informadas");
    }
  }

  private static void validarFiltroCombinadoComDatas(String agencia, String conta) {
  }




}
