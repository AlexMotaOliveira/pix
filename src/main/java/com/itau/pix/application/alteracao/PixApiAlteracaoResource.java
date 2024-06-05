package com.itau.pix.application.alteracao;


import com.itau.pix.api.AlteracaoApiDelegate;
import com.itau.pix.application.alteracao.impl.PixAlteracaoService;
import com.itau.pix.application.validation.ValidaRegras;
import com.itau.pix.infrastructure.entity.CadastroChavesPix;
import com.itau.pix.infrastructure.entity.ChavePixEntity;
import com.itau.pix.infrastructure.entity.CorrentistaEntity;
import com.itau.pix.model.Alterar200Response;
import com.itau.pix.model.Alterar200ResponsePix;
import com.itau.pix.model.AlterarRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PixApiAlteracaoResource implements AlteracaoApiDelegate {

  private final ValidaRegras validaRegras;
  private final PixAlteracaoService alteracaoService;

  private static Alterar200Response getResponse(CadastroChavesPix cadastroChavesPix) {
    CorrentistaEntity correntista = cadastroChavesPix.getCorrentista();
    ChavePixEntity chavePix = cadastroChavesPix.getChavePix();

    Alterar200Response response = new Alterar200Response();
    response.setTipoConta(correntista.getTipoConta().getValue());
    response.setNumeroAgencia(correntista.getNumeroAgencia());
    response.setNumeroConta((int) correntista.getNumeroConta());
    response.setNomeCorrentista(correntista.getNomeCorrentista());
    response.setSobrenomeCorrentista(correntista.getSobrenomeCorrentista());

    Alterar200ResponsePix pix = new Alterar200ResponsePix();
    pix.tipoChave(chavePix.getTipoChave().getValue());
    pix.setValorChave(chavePix.getValorChave());
    pix.setId(chavePix.getId());
    pix.setDataHoraInclusaoChave(chavePix.getDataHoraInclusaoDaChave());
    response.setPix(pix);
    return response;
  }

  @Override
  public ResponseEntity<Alterar200Response> alterar(UUID id, AlterarRequest alterarRequest) {
    Correntista cadastroChavesPix = new Correntista(alterarRequest);
    validaRegras.validar(cadastroChavesPix);

    CadastroChavesPix cadastroChavesPixEntity = alteracaoService.alterarChave(cadastroChavesPix, id);
    Alterar200Response response = getResponse(cadastroChavesPixEntity);
    return ResponseEntity.ok(response);
  }

}
