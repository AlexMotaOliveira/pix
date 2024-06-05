package com.itau.pix.application.inclusao;


import com.itau.pix.api.InclusaoApiDelegate;
import com.itau.pix.application.inclusao.impl.PixInclusaoService;
import com.itau.pix.application.validation.ValidaRegras;
import com.itau.pix.infrastructure.entity.CadastroChavesPix;
import com.itau.pix.infrastructure.entity.ChavePixEntity;
import com.itau.pix.infrastructure.entity.CorrentistaEntity;
import com.itau.pix.model.Incluir200Response;
import com.itau.pix.model.IncluirRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PixApiInclusaoResource implements InclusaoApiDelegate {

  private final ValidaRegras validaRegras;
  private final PixInclusaoService pixInclusaoService;


  @Override
  public ResponseEntity<Incluir200Response> incluir(IncluirRequest incluirRequest) {
    CorrentistaEntity correntistaEntity = new CorrentistaEntity(incluirRequest);
    ChavePixEntity chavePix = new ChavePixEntity(incluirRequest.getPix());
//    CadastroChavesPix cadastroChavesPix = new CadastroChavesPix(incluirRequest);
    validaRegras.validar(correntistaEntity);

    UUID uuid = pixInclusaoService.incluirChave(correntistaEntity, chavePix);
    Incluir200Response response = new Incluir200Response();
    response.setId(uuid);
    return ResponseEntity.ok(response);
  }

}
