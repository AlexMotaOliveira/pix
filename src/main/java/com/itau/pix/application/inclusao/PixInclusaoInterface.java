package com.itau.pix.application.inclusao;

import com.itau.pix.infrastructure.entity.CadastroChavesPix;

import java.util.UUID;

public interface PixInclusaoInterface {

  UUID incluirChave(CadastroChavesPix pixModel);

}
