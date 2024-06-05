package com.itau.pix.application.inativacao;

import com.itau.pix.infrastructure.entity.CadastroChavesPix;

import java.util.UUID;

public interface PixInativacaoInterface {

  CadastroChavesPix inativar(UUID uuid);


}
