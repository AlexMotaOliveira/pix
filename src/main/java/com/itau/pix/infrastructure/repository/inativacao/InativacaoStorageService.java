package com.itau.pix.infrastructure.repository.inativacao;

import com.itau.pix.infrastructure.entity.CadastroChavesPix;
import com.itau.pix.infrastructure.repository.CadastroChavePixRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InativacaoStorageService implements InativacaoStorage {

  private final CadastroChavePixRepository cadastroChavePixRepository;

  @Override
  public CadastroChavesPix buscarPorChavePix(UUID uuid) {
    return cadastroChavePixRepository.findByChavePix_Id(uuid);
  }

  @Override
  public CadastroChavesPix inativar(CadastroChavesPix cadastroChavesPix) {
    return cadastroChavePixRepository.save(cadastroChavesPix);
  }
}
