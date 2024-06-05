package com.itau.pix.infrastructure.repository.inativacao;


import com.itau.pix.infrastructure.entity.CadastroChavesPix;

import java.util.UUID;

public interface InativacaoStorage {

  CadastroChavesPix inativar(CadastroChavesPix cadastroChavesPix);

  CadastroChavesPix buscarPorChavePix(UUID uuid);
}
