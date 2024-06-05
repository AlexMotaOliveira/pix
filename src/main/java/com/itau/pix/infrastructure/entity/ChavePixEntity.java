package com.itau.pix.infrastructure.entity;

import com.itau.pix.application.validation.ValorChaveGroupSequenceProvider;
import com.itau.pix.application.validation.annotation.Celular;
import com.itau.pix.application.validation.annotation.ChaveAleatoria;
import com.itau.pix.application.validation.annotation.ContemPonto;
import com.itau.pix.application.validation.interfaces.ValidarChaveAleatoria;
import com.itau.pix.application.validation.interfaces.ValidarCnpj;
import com.itau.pix.application.validation.interfaces.ValidarCpf;
import com.itau.pix.application.validation.interfaces.ValidarEmail;
import com.itau.pix.application.validation.interfaces.ValidarTelefone;
import com.itau.pix.domain.enums.SituacaoChave;
import com.itau.pix.domain.enums.TipoChave;
import com.itau.pix.model.IncluirRequestPix;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_CHAVE_PIX")
@GroupSequenceProvider(ValorChaveGroupSequenceProvider.class)
public class ChavePixEntity {

  @Id
  @UuidGenerator
  @GeneratedValue(strategy = GenerationType.UUID)
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

  @Column(name = "SITUACAO_CHAVE", nullable = false)
  private SituacaoChave situacaoChave;

  @CreationTimestamp
  @Column(name = "DATA_HORA_INCLUSAO_DA_CHAVE", updatable = false, nullable = false)
  private OffsetDateTime dataHoraInclusaoDaChave;

  @Column(name = "DATA_HORA_INATIVACAO_DA_CHAVE", length = 4)
  private OffsetDateTime dataHoraInativacaoDaChave;


  public ChavePixEntity() {

  }

  public ChavePixEntity(IncluirRequestPix pix) {
    this.id = UUID.nameUUIDFromBytes(pix.getValorChave().getBytes());
    this.tipoChave = TipoChave.fromString(pix.getTipoChave());
    this.valorChave = pix.getValorChave();
    this.situacaoChave = SituacaoChave.ATIVA;
  }


  public ChavePixEntity(String id) {
    this.id = UUID.fromString(id);
  }


}
