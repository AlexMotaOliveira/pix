package com.itau.pix.application.operacoes.consulta;

import com.itau.pix.application.operacoes.consulta.dto.CadastroChavesPixDTO;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PixConsutaInterface {

  Page<CadastroChavesPixDTO> consultar(UUID id, String nomeCorrentista,
                                       String tipoChave, String agencia, String conta,
                                       String paginacao, String quantidade);
}
