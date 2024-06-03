package com.itau.pix.entity;

import com.itau.pix.annotation.Celular;
import com.itau.pix.annotation.ChaveAleatoria;
import com.itau.pix.annotation.ContemPonto;
import com.itau.pix.annotation.validation.ValorChaveGroupSequenceProvider;
import com.itau.pix.annotation.validation.interfaces.ValidarChaveAleatoria;
import com.itau.pix.annotation.validation.interfaces.ValidarCnpj;
import com.itau.pix.annotation.validation.interfaces.ValidarCpf;
import com.itau.pix.annotation.validation.interfaces.ValidarEmail;
import com.itau.pix.annotation.validation.interfaces.ValidarTelefone;
import com.itau.pix.enums.SituacaoChave;
import com.itau.pix.enums.TipoChave;
import com.itau.pix.enums.TipoCliente;
import com.itau.pix.enums.TipoConta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_PIX")
@GroupSequenceProvider(ValorChaveGroupSequenceProvider.class)
public class Pix {

  @Id
  private UUID id;

  @Column(name = "TIPO_CHAVE", nullable = false, updatable = false)
  private TipoChave tipoChave;

  @NotBlank
  @Size(max = 77)
  @CPF(groups = {ValidarCpf.class})
  @CNPJ(groups = {ValidarCnpj.class})
  @ContemPonto(groups = {ValidarCpf.class, ValidarCnpj.class})
  @Email(groups = ValidarEmail.class)
  @Celular(groups = ValidarTelefone.class)
  @ChaveAleatoria(groups = ValidarChaveAleatoria.class)
  @Column(name = "VALOR_CHAVE", nullable = false, length = 77, updatable = false)
  private String valorChave;

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

  @Column(name = "IDENTIFICACAO_CLIENTE", length = 14, nullable = false)
  private String idCliente;

  @Column(name = "SITUACAO_CHAVE", nullable = false)
  private SituacaoChave situacaoChave;

  @CreationTimestamp
  @Column(name = "DATA_HORA_INCLUSAO_DA_CHAVE", updatable = false, nullable = false)
  private Timestamp dataHoraInclusaoDaChave;

  @Column(name = "DATA_HORA_INATIVACAO_DA_CHAVE", length = 4)
  private Timestamp dataHoraInativacaoDaChave;

  @Column(name = "TIPO_CLIENTE", nullable = false, updatable = false)
  private TipoCliente tipoCliente;

  public Pix(com.itau.pix.model.IncluirRequest pix) {
    //TODO ajustar a chave para UUID
    this.id = UUID.nameUUIDFromBytes(pix.getValorChave().getBytes());
    this.tipoChave = TipoChave.fromString(pix.getTipoChave());
    this.valorChave = pix.getValorChave();
    this.tipoConta = TipoConta.fromString(pix.getTipoConta());
    this.numeroAgencia = pix.getNumeroAgencia();
    this.numeroConta = pix.getNumeroConta();
    this.nomeCorrentista = pix.getNomeCorrentista();
    this.sobrenomeCorrentista = pix.getSobrenomeCorrentista();
    this.idCliente = pix.getIdCliente();
    this.tipoCliente = TipoCliente.fromString(pix.getTipoCliente());
    this.situacaoChave = SituacaoChave.ATIVA;
  }

  public Pix(com.itau.pix.model.AtualizarRequest pix) {
    //TODO ajustar a chave para UUID
    this.id = pix.getId();
    this.tipoConta = TipoConta.fromString(pix.getTipoConta());
    this.numeroAgencia = pix.getNumeroAgencia();
    this.numeroConta = pix.getNumeroConta();
    this.nomeCorrentista = pix.getNomeCorrentista();
    this.sobrenomeCorrentista = pix.getSobrenomeCorrentista();
  }

  public Pix() {
  }

}
