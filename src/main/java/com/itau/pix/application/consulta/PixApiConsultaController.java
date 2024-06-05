package com.itau.pix.application.consulta;


import com.itau.pix.application.consulta.impl.PixConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@RestController
public class PixApiConsultaController {

  private final PixConsultaService pixConsultaService;

  @PostMapping(value = "/consulta/{idCorrentista}")
  public ResponseEntity<String> incluir(@PathVariable String idCorrentista,
                                        @RequestParam(name = "id", required = false) UUID id,
                                        @RequestParam(name = "tipoChave", required = false) String tipoChave,
                                        @RequestParam(name = "valorChave", required = false) String valorChave,
                                        @RequestParam(name = "agencia", required = false) String agencia,
                                        @RequestParam(name = "conta", required = false) String conta) {

    pixConsultaService.consultar(id, idCorrentista, tipoChave, valorChave, agencia, conta);
    return ResponseEntity.ok("uuid");
  }

}
