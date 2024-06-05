package com.itau.pix.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_CADASTRO_CHAVES_PIX")
public class CadastroChavesPixEntity {

  @Id
  private UUID id;

  @UuidGenerator
  private UUID idChavePix;

  private Long idCorrentista;

  public CadastroChavesPixEntity(Long idCorrentista, UUID idChavePix) {
    this.id = UUID.nameUUIDFromBytes((idCorrentista.byteValue() + idChavePix.toString()).getBytes(StandardCharsets.UTF_8));
    this.idChavePix = idChavePix;
    this.idCorrentista = idCorrentista;
  }

  public CadastroChavesPixEntity() {

  }
}
