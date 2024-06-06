package com.itau.pix.application.operacoes.inativacao;

import com.itau.pix.application.operacoes.inativacao.dto.CadastroChavesPixDTO;
import com.itau.pix.application.operacoes.inativacao.impl.PixInativacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@RestController
public class PixApiInativacaoController {

  private final PixInativacaoService alteracaoService;

  @DeleteMapping("/cadastro/{idCorrentista}/pix/{idChavePix}")
  public ResponseEntity<CadastroChavesPixDTO> inativar(@PathVariable String idCorrentista, @PathVariable UUID idChavePix) {
    CadastroChavesPixDTO cadastroChavesPixDTO = alteracaoService.inativar(idChavePix, idCorrentista);
    return ResponseEntity.ok(cadastroChavesPixDTO);
  }

}
