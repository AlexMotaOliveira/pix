package com.itau.pix.application.operacoes.inclusao;

import com.itau.pix.domain.CadastroChavesPix;

import java.util.UUID;

public interface PixInclusaoInterface {

  UUID incluirChave(CadastroChavesPix cadastroChavesPix);
}
