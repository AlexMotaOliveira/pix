package com.itau.pix.application.alteracao;


import com.itau.pix.application.alteracao.dto.CadastroChavesPixDTO;
import com.itau.pix.application.alteracao.dto.CorrentistaRequestDTO;
import com.itau.pix.application.alteracao.impl.PixAlteracaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class PixApiAlteracaoController {

  private final PixAlteracaoService alteracaoService;

  @PatchMapping("/cadastro/{idCorrentista}/pix/{idChavePix}")
  public ResponseEntity<CadastroChavesPixDTO> inativar(@PathVariable String idCorrentista,
                                                       @PathVariable UUID idChavePix,
                                                       @RequestBody @Valid CorrentistaRequestDTO correntista) {
    CadastroChavesPixDTO cadastroChavesPixDTO = alteracaoService.alterarChave(correntista, idChavePix, idCorrentista);
    return ResponseEntity.ok(cadastroChavesPixDTO);
  }

  //https://www.baeldung.com/spring-rest-openapi-documentation
}
