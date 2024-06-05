package com.itau.pix.infrastructure.repository.alteracao;

import com.itau.pix.infrastructure.entity.CadastroChavesPix;
import com.itau.pix.infrastructure.repository.CadastroChavePixRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlteracaoStorageService implements AlteracaoStorage {

  private final CadastroChavePixRepository cadastroChavePixRepository;

  @Override
  public CadastroChavesPix alterar(CadastroChavesPix correntista) {
    return cadastroChavePixRepository.save(correntista);
  }

  @Override
  public CadastroChavesPix buscarPorChavePix(UUID uuid) {
    return cadastroChavePixRepository.findByChavePix_Id(uuid);
  }
}
