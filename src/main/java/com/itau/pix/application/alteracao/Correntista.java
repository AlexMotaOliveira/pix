package com.itau.pix.application.alteracao;

import com.itau.pix.domain.enums.TipoConta;
import com.itau.pix.model.AlterarRequest;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Correntista {

  private final TipoConta tipoConta;

  @Min(1)
  @Max(9999)
  private final int numeroAgencia;

  @Min(1)
  @Max(99999999)
  private final long numeroConta;

  @NotBlank
  private final String nomeCorrentista;

  private final String sobrenomeCorrentista;

  public Correntista(AlterarRequest pix) {
    //TODO ajustar a chave para UUID
    this.tipoConta = TipoConta.fromString(pix.getTipoConta());
    this.numeroAgencia = pix.getNumeroAgencia();
    this.numeroConta = pix.getNumeroConta();
    this.nomeCorrentista = pix.getNomeCorrentista();
    this.sobrenomeCorrentista = pix.getSobrenomeCorrentista();
  }

}
