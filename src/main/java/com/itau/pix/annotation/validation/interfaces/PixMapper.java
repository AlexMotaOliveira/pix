package com.itau.pix.annotation.validation.interfaces;

import com.itau.pix.model.Pix;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

//@Mapper(componentModel = "spring")
@Component
public interface PixMapper {
  void PixToEntity(Pix dto, @MappingTarget com.itau.pix.entity.Pix entity);

  void PixToDTO(com.itau.pix.entity.Pix entity, @MappingTarget Pix dto);
}
