package com.itau.pix.application.consulta;

import java.util.UUID;

public interface PixConsutaInterface {




  void consultar(UUID id, String idCorrentista, String tipoChave, String valorChave, String agencia, String conta);
}
