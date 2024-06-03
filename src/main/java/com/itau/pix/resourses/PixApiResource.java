package com.itau.pix.resourses;


import com.itau.pix.annotation.validation.ValidaRegras;
import com.itau.pix.api.PixApiDelegate;
import com.itau.pix.model.AtualizarRequest;
import com.itau.pix.model.IncluirRequest;
import com.itau.pix.model.Pix;
import com.itau.pix.service.PixService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PixApiResource implements PixApiDelegate {

  private final ValidaRegras validaRegras;
  private final PixService pixService;

  @Override
  public ResponseEntity<UUID> incluir(IncluirRequest pix) {
    com.itau.pix.entity.Pix pixEntity = new com.itau.pix.entity.Pix(pix);
    validaRegras.validar(pixEntity);

    UUID uuid = pixService.incluirChave(pixEntity);
    return ResponseEntity.ok(uuid);
  }


  @Override
  public ResponseEntity<Pix> inativar(UUID id) {
    com.itau.pix.entity.Pix pixEntity = pixService.inativar(id);
    Pix pix = new Pix();
    BeanUtils.copyProperties(pixEntity, pix);
    return ResponseEntity.ok(pix);
  }

  //TODO validar no token se aquele id pertence a pessoa dona do token
  @Override
  public ResponseEntity<Pix> atualizar(AtualizarRequest pix) {
    com.itau.pix.entity.Pix pixEntity = new com.itau.pix.entity.Pix(pix);
//    validaRegras.validar(pixEntity); //TODO criar um classe para validar as regras especificas de alteração

    com.itau.pix.entity.Pix pixEntity2 = pixService.alterarChave(pixEntity);
    Pix pixResponse = new Pix();
    BeanUtils.copyProperties(pixEntity2, pixResponse);
    return ResponseEntity.ok(pixResponse);
  }

  @Override
  public ResponseEntity<List<Pix>> listarChavesPix(UUID id,
                                                   String tipoChave,
                                                   String agenciaConta,
                                                   String nomeCorrentista,
                                                   OffsetDateTime dataCriacaoChave,
                                                   OffsetDateTime dataInativacaoChave) {
    List<com.itau.pix.entity.Pix> pixes = pixService.listarChaves(id, tipoChave, agenciaConta, nomeCorrentista, dataCriacaoChave, dataInativacaoChave);
    List<Pix> pixResponse = List.of(new Pix());
    BeanUtils.copyProperties(pixes, pixResponse);
    return null;
  }
}
