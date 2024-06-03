package com.itau.pix.infrastructure.repository.consulta;


import com.itau.pix.application.operacoes.consulta.dto.CadastroChavesPixDTO;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ConsultaStorage {


  Page<CadastroChavesPixDTO> consultarId(UUID uuid);


  void consultarFiltroCombinado(String nomeCorrentista, String tipoChave, String agencia, String conta, String quantidade, String paginacao);
}
