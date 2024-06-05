package com.itau.pix.infrastructure.repository.inclusao;

import com.itau.pix.domain.CadastroChavesPix;
import com.itau.pix.domain.enums.SituacaoChave;
import com.itau.pix.infrastructure.entity.CadastroChavesPixEntity;
import com.itau.pix.infrastructure.entity.ChavePixEntity;
import com.itau.pix.infrastructure.entity.CorrentistaEntity;
import com.itau.pix.infrastructure.repository.CadastroChavePixRepository;
import com.itau.pix.infrastructure.repository.ChavePixRepository;
import com.itau.pix.infrastructure.repository.CorrentistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class IncluirStorageService implements IncluirStorage {

  private final CadastroChavePixRepository cadastroChavePixRepository;
  private final CorrentistaRepository correntistaRepository;
  private final ChavePixRepository chavePixRepository;


  @Override
  public boolean existsByIdAndSituacaoChave(UUID id, SituacaoChave situacaoChave) {
    return chavePixRepository.existsByIdAndSituacaoChave(id, situacaoChave);
  }

  @Override
  public int countByIdCorrentistaAndNumeroAgenciaAndNumeroConta(Long idCorrentista, int numeroAgencia, long numeroConta) {
    return correntistaRepository.countByIdCorrentistaAndNumeroAgenciaAndNumeroConta(idCorrentista, numeroAgencia, numeroConta);
  }

  @Override
  public UUID incluir(CadastroChavesPix cadastroChavesPix) {
    CorrentistaEntity correntista = new CorrentistaEntity(cadastroChavesPix.correntista());
    ChavePixEntity chavePixEntity = new ChavePixEntity(cadastroChavesPix.chavePix());

    CorrentistaEntity correntistaEntity = correntistaRepository.save(correntista);
    UUID id = chavePixRepository.save(chavePixEntity).getId();

    CadastroChavesPixEntity cadastroChavesPixEntity = new CadastroChavesPixEntity(correntistaEntity.getIdCorrentista(), id);
    cadastroChavePixRepository.save(cadastroChavesPixEntity);
    return id;
  }
}
