package com.itau.pix.infrastructure.repository.alteracao;


import com.itau.pix.infrastructure.entity.CadastroChavesPix;

import java.util.UUID;

public interface AlteracaoStorage {

  CadastroChavesPix alterar(CadastroChavesPix correntista);

  CadastroChavesPix buscarPorChavePix(UUID uuid);
}
