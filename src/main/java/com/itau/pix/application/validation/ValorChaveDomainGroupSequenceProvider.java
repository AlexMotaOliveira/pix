package com.itau.pix.application.validation;

import com.itau.pix.domain.ChavePix;
import com.itau.pix.domain.enums.TipoChave;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class ValorChaveDomainGroupSequenceProvider implements DefaultGroupSequenceProvider<ChavePix> {


  @Override
  public List<Class<?>> getValidationGroups(ChavePix chavePix) {
    List<Class<?>> groups = new ArrayList<>();
    groups.add(ChavePix.class);
    if (chavePix != null) {
      TipoChave tipoConta = TipoChave.fromString(chavePix.tipoChave());
      groups.add(tipoConta.getGroup());
    }
    return groups;
  }
}
