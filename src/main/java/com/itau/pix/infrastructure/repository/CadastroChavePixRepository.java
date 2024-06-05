package com.itau.pix.infrastructure.repository;

import com.itau.pix.infrastructure.entity.CadastroChavesPix;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CadastroChavePixRepository extends JpaRepository<CadastroChavesPix, Long> {

  CadastroChavesPix findByChavePix_Id(UUID uuid);
}
