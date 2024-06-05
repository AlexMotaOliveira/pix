package com.itau.pix.infrastructure.repository;

import com.itau.pix.infrastructure.entity.CadastroChavesPixEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadastroChavePixRepository extends JpaRepository<CadastroChavesPixEntity, Long> {

//  CadastroChavesPixEntity findByChavePix_Id(UUID uuid);
}
