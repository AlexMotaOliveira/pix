package com.itau.pix.infrastructure.entity;

import com.itau.pix.application.validation.ValorChaveEntityGroupSequenceProvider;
import com.itau.pix.application.validation.annotation.Celular;
import com.itau.pix.application.validation.annotation.ChaveAleatoria;
import com.itau.pix.application.validation.annotation.ContemPonto;
import com.itau.pix.application.validation.interfaces.ValidarChaveAleatoria;
import com.itau.pix.application.validation.interfaces.ValidarCnpj;
import com.itau.pix.application.validation.interfaces.ValidarCpf;
import com.itau.pix.application.validation.interfaces.ValidarEmail;
import com.itau.pix.application.validation.interfaces.ValidarTelefone;
import com.itau.pix.domain.ChavePix;
import com.itau.pix.domain.enums.SituacaoChave;
import com.itau.pix.domain.enums.TipoChave;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_CHAVE_PIX")
@GroupSequenceProvider(ValorChaveEntityGroupSequenceProvider.class)
public class ChavePixEntity {

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

  @Column(name = "SITUACAO_CHAVE", nullable = false)
  private SituacaoChave situacaoChave;

  @CreationTimestamp
  @Column(name = "DATA_HORA_INCLUSAO_DA_CHAVE", updatable = false, nullable = false)
  private OffsetDateTime dataHoraInclusaoDaChave;

  @Column(name = "DATA_HORA_INATIVACAO_DA_CHAVE", length = 4)
  private OffsetDateTime dataHoraInativacaoDaChave;

  public ChavePixEntity() {

  }

  public ChavePixEntity(String id) {
    this.id = UUID.fromString(id);
  }

  public ChavePixEntity(ChavePix chavePix) {
    this.id = UUID.nameUUIDFromBytes(chavePix.valorChave().getBytes(StandardCharsets.UTF_8));
    this.tipoChave = TipoChave.fromString(chavePix.tipoChave());
    this.valorChave = chavePix.valorChave();
    this.situacaoChave = SituacaoChave.ATIVA;
  }
}
