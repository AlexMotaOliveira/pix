package com.itau.pix.domain;

import com.itau.pix.domain.enums.SituacaoChave;
import com.itau.pix.domain.enums.TipoChave;
import com.itau.pix.application.validation.annotation.Celular;
import com.itau.pix.application.validation.annotation.ChaveAleatoria;
import com.itau.pix.application.validation.annotation.ContemPonto;
import com.itau.pix.application.validation.interfaces.ValidarChaveAleatoria;
import com.itau.pix.application.validation.interfaces.ValidarCnpj;
import com.itau.pix.application.validation.interfaces.ValidarCpf;
import com.itau.pix.application.validation.interfaces.ValidarEmail;
import com.itau.pix.application.validation.interfaces.ValidarTelefone;

import com.itau.pix.model.IncluirRequestPix;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class ChavePix {

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
  private Timestamp dataHoraInclusaoDaChave;

  @Setter
  @Column(name = "DATA_HORA_INATIVACAO_DA_CHAVE", length = 4)
  private Timestamp dataHoraInativacaoDaChave;

  public ChavePix() {

  }

  public ChavePix(IncluirRequestPix pix) {
    this.id = UUID.nameUUIDFromBytes(pix.getValorChave().getBytes());
    this.tipoChave = TipoChave.fromString(pix.getTipoChave());
    this.valorChave = pix.getValorChave();
  }


  public ChavePix(UUID id) {
    this.id = id;
  }
}
