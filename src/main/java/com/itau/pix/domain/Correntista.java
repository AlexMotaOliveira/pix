package com.itau.pix.domain;

import com.itau.pix.domain.enums.TipoConta;
import com.itau.pix.domain.enums.TipoCorrentista;
import com.itau.pix.model.AlterarRequest;
import com.itau.pix.model.IncluirRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Correntista {

  @Id
  private String idCorrentista;

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

  @ManyToMany
  private List<ChavePix> chavePixes = new ArrayList<>();

  public Correntista(IncluirRequest request) {
    this.tipoConta = TipoConta.fromString(request.getTipoConta());
    this.numeroAgencia = request.getNumeroAgencia();
    this.numeroConta = request.getNumeroConta();
    this.nomeCorrentista = request.getNomeCorrentista();
    this.sobrenomeCorrentista = request.getSobrenomeCorrentista();
    this.idCorrentista = request.getIdCorrentista();
    this.tipoCorrentista = TipoCorrentista.fromString(request.getTipoCorrentista());
    ChavePix chavePixRequest = new ChavePix(request.getPix());
    this.chavePixes.add(chavePixRequest);
  }

  public Correntista(AlterarRequest pix, UUID id) {
    //TODO ajustar a chave para UUID
    this.tipoConta = TipoConta.fromString(pix.getTipoConta());
    this.numeroAgencia = pix.getNumeroAgencia();
    this.numeroConta = pix.getNumeroConta();
    this.nomeCorrentista = pix.getNomeCorrentista();
    this.sobrenomeCorrentista = pix.getSobrenomeCorrentista();
    this.chavePixes.add(new ChavePix(id));
  }

  public Correntista() {
  }

}
