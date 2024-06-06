package com.itau.pix.infrastructure.repository;

import com.itau.pix.infrastructure.entity.CadastroChavesPixEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CadastroChavePixRepository extends JpaRepository<CadastroChavesPixEntity, Long> {

  CadastroChavesPixEntity findByIdChavePix(UUID uuid);

}
