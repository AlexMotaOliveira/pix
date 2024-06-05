package com.itau.pix.infrastructure.entity;

import com.itau.pix.domain.enums.TipoConta;
import com.itau.pix.domain.enums.TipoCorrentista;
import com.itau.pix.model.AlterarRequest;
import com.itau.pix.model.IncluirRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "TB_CORRENTISTA")
public class CorrentistaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  @Column(name = "NOME_CORRENTISTA", nullable = false, length = 30)
  private String nomeCorrentista;

  @Column(name = "SOBRENOME_CORRENTISTA", length = 45)
  private String sobrenomeCorrentista;


  @Column(name = "TIPO_CLIENTE", nullable = false, updatable = false)
  private TipoCorrentista tipoCorrentista;

  @OneToMany(cascade = CascadeType.PERSIST)
  private Set<CadastroChavesPix> chavePix = new HashSet<>();

  public CorrentistaEntity(IncluirRequest request) {
    this.tipoConta = TipoConta.fromString(request.getTipoConta());
    this.numeroAgencia = request.getNumeroAgencia();
    this.numeroConta = request.getNumeroConta();
    this.nomeCorrentista = request.getNomeCorrentista();
    this.sobrenomeCorrentista = request.getSobrenomeCorrentista();
    this.idCorrentista = Long.valueOf(request.getIdCorrentista());
    this.tipoCorrentista = TipoCorrentista.fromString(request.getTipoCorrentista());
  }

  public CorrentistaEntity(AlterarRequest pix) {
    //TODO ajustar a chave para UUID
    this.tipoConta = TipoConta.fromString(pix.getTipoConta());
    this.numeroAgencia = pix.getNumeroAgencia();
    this.numeroConta = pix.getNumeroConta();
    this.nomeCorrentista = pix.getNomeCorrentista();
    this.sobrenomeCorrentista = pix.getSobrenomeCorrentista();
  }

  public CorrentistaEntity() {
  }

}
