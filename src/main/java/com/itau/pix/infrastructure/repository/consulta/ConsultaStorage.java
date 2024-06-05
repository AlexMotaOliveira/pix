package com.itau.pix.infrastructure.repository.consulta;


import com.itau.pix.infrastructure.entity.ChavePixEntity;

import java.util.UUID;

public interface ConsultaStorage {


  ChavePixEntity consultarId(UUID uuid);

  void consultarFiltroCombinado(String idCorrentista, String tipoChave, String valorChave, String agencia, String conta);
}
