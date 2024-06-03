package com.itau.pix.infrastructure.repository;

import com.itau.pix.domain.enums.SituacaoChave;
import com.itau.pix.domain.enums.TipoChave;
import com.itau.pix.infrastructure.entity.ChavePixEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChavePixRepository extends JpaRepository<ChavePixEntity, UUID> {

  boolean existsByIdAndSituacaoChave(UUID id, SituacaoChave situacaoChave);

  //TODO Fazer usando Projection
  List<ChavePixEntity> findAllByTipoChave(TipoChave tipoChave, Pageable pageable);

}
