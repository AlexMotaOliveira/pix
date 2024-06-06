package com.itau.pix.application.operacoes.consulta;


import com.itau.pix.application.operacoes.consulta.dto.CadastroChavesPixDTO;
import com.itau.pix.application.operacoes.consulta.impl.PixConsultaService;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@RestController
public class PixApiConsultaController {

  private final PixConsultaService pixConsultaService;

  @GetMapping("/consulta")
  public ResponseEntity<Page<CadastroChavesPixDTO>> consultar(
    @RequestParam(name = "id", required = false) UUID id,
    @RequestParam(name = "tipoChave", required = false) String tipoChave,
    @RequestParam(name = "nomeCorrentista", required = false) String nomeCorrentista,
    @RequestParam(name = "agencia", required = false) String agencia,
    @RequestParam(name = "conta", required = false) String conta,
    @RequestParam(name = "paginacao", required = false, defaultValue = "0") @Pattern(regexp = "^\\d$") String paginacao,
    @RequestParam(name = "quantidade", required = false, defaultValue = "0") @Pattern(regexp = "^\\d$") String quantidade) {

    Page<CadastroChavesPixDTO> consultar = pixConsultaService.consultar(id, nomeCorrentista, tipoChave, agencia, conta, paginacao, quantidade);
    return ResponseEntity.ok(consultar);
  }

}
