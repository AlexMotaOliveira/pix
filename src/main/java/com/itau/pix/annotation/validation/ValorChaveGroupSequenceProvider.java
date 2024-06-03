package com.itau.pix.annotation.validation;

import com.itau.pix.entity.Pix;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class ValorChaveGroupSequenceProvider implements DefaultGroupSequenceProvider<Pix> {

  @Override
  public List<Class<?>> getValidationGroups(Pix pix) {
    List<Class<?>> groups = new ArrayList<>();
    groups.add(Pix.class);

    if (pix != null && pix.getValorChave() != null) {
      groups.add(pix.getTipoChave().getGroup());
    }

    return groups;
  }
}
