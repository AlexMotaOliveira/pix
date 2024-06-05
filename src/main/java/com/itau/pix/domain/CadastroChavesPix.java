package com.itau.pix.domain;

import com.itau.pix.infrastructure.entity.ChavePixEntity;
import com.itau.pix.infrastructure.entity.CorrentistaEntity;


public class CadastroChavesPix {

  private Long id;

  CorrentistaEntity correntista;

  ChavePixEntity chavePix;

  public CadastroChavesPix(CorrentistaEntity correntista, ChavePixEntity chavePix) {
    this.correntista = correntista;
    this.chavePix = chavePix;
  }

  public CadastroChavesPix() {

  }
}
