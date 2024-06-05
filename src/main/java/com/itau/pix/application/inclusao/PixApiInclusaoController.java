package com.itau.pix.application.inclusao;


import com.itau.pix.application.inclusao.impl.PixInclusaoService;
import com.itau.pix.domain.CadastroChavesPix;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@RestController
public class PixApiInclusaoController {

  private final PixInclusaoService pixInclusaoService;

  @PostMapping("/teste")
  public ResponseEntity<UUID> incluir(@RequestBody @Valid CadastroChavesPix cadastroChavesPix) {
    UUID uuid = pixInclusaoService.incluirChave(cadastroChavesPix);
    return ResponseEntity.ok(uuid);
  }

}
