package com.itau.pix.application.inativacao;


import com.itau.pix.api.InativacaoApiDelegate;
import com.itau.pix.application.inativacao.impl.PixInativacaoService;
import com.itau.pix.infrastructure.entity.CadastroChavesPix;
import com.itau.pix.infrastructure.entity.ChavePixEntity;
import com.itau.pix.infrastructure.entity.CorrentistaEntity;
import com.itau.pix.model.Alterar200Response;
import com.itau.pix.model.Alterar200ResponsePix;
import com.itau.pix.model.Pix;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PixApiInativacaoResource implements InativacaoApiDelegate {


  private final PixInativacaoService alteracaoService;


  @Override
  public ResponseEntity<Pix> inativar(UUID id) {
    CadastroChavesPix inativar = alteracaoService.inativar(id);
    return InativacaoApiDelegate.super.inativar(id);
  }

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


}
