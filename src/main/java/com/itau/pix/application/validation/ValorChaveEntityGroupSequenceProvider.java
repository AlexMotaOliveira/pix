package com.itau.pix.application.validation;

import com.itau.pix.infrastructure.entity.ChavePixEntity;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class ValorChaveEntityGroupSequenceProvider implements DefaultGroupSequenceProvider<ChavePixEntity> {


  @Override
  public List<Class<?>> getValidationGroups(ChavePixEntity chavePixEntity) {
    List<Class<?>> groups = new ArrayList<>();
    groups.add(ChavePixEntity.class);

    if (chavePixEntity != null && chavePixEntity.getTipoChave() != null) {
      groups.add(chavePixEntity.getTipoChave().getGroup());
    }
    return groups;
  }
}
