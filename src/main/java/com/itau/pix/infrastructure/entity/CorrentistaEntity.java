package com.itau.pix.infrastructure.entity;

import com.itau.pix.domain.Correntista;
import com.itau.pix.domain.enums.TipoConta;
import com.itau.pix.domain.enums.TipoCorrentista;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_CORRENTISTA")
public class CorrentistaEntity {

  @Id
  private Long idCorrentista;

  @Column(name = "TIPO_CONTA", nullable = false)
  private TipoConta tipoConta;

  @Min(1)
  @Max(9999)
  @Column(name = "NUMERO_AGENCIA", nullable = false, length = 4)
  private int numeroAgencia;

  @Min(1)
  @Max(99999999)
  @Column(name = "NUMERO_CONTA", nullable = false, length = 8)
  private long numeroConta;

  @NotBlank
  @Column(name = "NOME_CORRENTISTA", nullable = false, length = 30)
  private String nomeCorrentista;

  @Column(name = "SOBRENOME_CORRENTISTA", length = 45)
  private String sobrenomeCorrentista;


  @Column(name = "TIPO_CLIENTE", nullable = false, updatable = false)
  private TipoCorrentista tipoCorrentista;

  public CorrentistaEntity() {
  }

  public CorrentistaEntity(Correntista correntista) {
    this.tipoConta = TipoConta.fromString(correntista.tipoConta());
    this.numeroAgencia = correntista.numeroAgencia();
    this.numeroConta = correntista.numeroConta();
    this.nomeCorrentista = correntista.nomeCorrentista();
    this.sobrenomeCorrentista = correntista.sobrenomeCorrentista();
    this.idCorrentista = Long.valueOf(correntista.idCorrentista());
    this.tipoCorrentista = TipoCorrentista.fromString(correntista.tipoCorrentista());
  }
}
