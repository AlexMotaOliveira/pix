package com.itau.pix.infrastructure.entity;

import com.itau.pix.model.AlterarRequest;
import com.itau.pix.model.IncluirRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_CADASTRO_CHAVES_PIX")
public class CadastroChavesPix {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  @ManyToOne(cascade = CascadeType.DETACH)
  CorrentistaEntity correntista;

  @ManyToOne(cascade = CascadeType.DETACH)
  ChavePixEntity chavePix;

  public CadastroChavesPix() {

  }

  public CadastroChavesPix(IncluirRequest incluirRequest) {
    this.correntista = new CorrentistaEntity(incluirRequest);
    this.chavePix = new ChavePixEntity(incluirRequest.getPix());
  }


  public CadastroChavesPix(AlterarRequest alterarRequest, String id) {
    this.correntista = new CorrentistaEntity(alterarRequest);
    this.chavePix = new ChavePixEntity(id);
  }
}
