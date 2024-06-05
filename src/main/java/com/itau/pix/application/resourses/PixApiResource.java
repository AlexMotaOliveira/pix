//package com.itau.pix.application.resourses;
//
//
//import com.itau.pix.application.service.PixService;
//import com.itau.pix.application.validation.ValidaRegras;
//import com.itau.pix.infrastructure.entity.CorrentistaEntity;
//import com.itau.pix.infrastructure.entity.PixModel;
//import com.itau.pix.api.PixCadastroApiDelegate;
//import com.itau.pix.model.AtualizarRequest;
//import com.itau.pix.model.IncluirRequest;
//import com.itau.pix.model.Correntista;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.BeanUtils;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//@Component
//@RequiredArgsConstructor
//public class PixApiResource implements PixCadastroApiDelegate {
//
//  private final ValidaRegras validaRegras;
//  private final PixService pixService;
//
//  @Override
//  public ResponseEntity<UUID> incluir(IncluirRequest request) {
//    CorrentistaEntity correntistaEntity = new CorrentistaEntity(request);
//    validaRegras.validar(correntistaEntity);
//
//    UUID uuid = pixService.incluirChave(correntistaEntity);
//    return ResponseEntity.ok(uuid);
//  }
//
//
//  @Override
//  public ResponseEntity<Correntista> atualizar(UUID id, AtualizarRequest atualizarRequest) {
//    PixModel pixModelEntity = new PixModel(atualizarRequest, id);
////    validaRegras.validar(pixModelEntity); //TODO criar um classe para validar as regras especificas de alteração
//
//    PixModel pixModelEntity2 = pixService.alterarChave(pixModelEntity);
//    Correntista pixResponse = new Correntista();
//    BeanUtils.copyProperties(pixModelEntity2, pixResponse);
//    return ResponseEntity.ok(pixResponse);
//  }
//
//  @Override
//  public ResponseEntity<Correntista> inativar(UUID id) {
//    PixModel pixModelEntity = pixService.inativar(id);
//    Correntista pix = new Correntista();
//    BeanUtils.copyProperties(pixModelEntity, pix);
//    return ResponseEntity.ok(pix);
//  }
//
//}
