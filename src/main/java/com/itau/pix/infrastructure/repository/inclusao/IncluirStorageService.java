package com.itau.pix.infrastructure.repository.inclusao;

import com.itau.pix.domain.enums.SituacaoChave;
import com.itau.pix.infrastructure.entity.CadastroChavesPix;
import com.itau.pix.infrastructure.entity.ChavePixEntity;
import com.itau.pix.infrastructure.entity.CorrentistaEntity;
import com.itau.pix.infrastructure.repository.CadastroChavePixRepository;
import com.itau.pix.infrastructure.repository.ChavePixRepository;
import com.itau.pix.infrastructure.repository.CorrentistaRepository;
import com.itau.pix.model.IncluirRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IncluirStorageService implements IncluirStorage {

  private final CadastroChavePixRepository cadastroChavePixRepository;
  private final CorrentistaRepository correntistaRepository;
  private final ChavePixRepository chavePixRepository;


  @Override
  public UUID incluir(CadastroChavesPix correntista) {
    correntista.getCorrentista().setIdCorrentista(null);
    CadastroChavesPix cadastroChavesPixEntity = cadastroChavePixRepository.save(correntista);
    return cadastroChavesPixEntity.getChavePix().getId();
  }


//  public UUID incluir(CadastroChavesPix correntista) {
//    correntista.getCorrentista().setIdCorrentista(null);
//    CadastroChavesPix cadastroChavesPixEntity = cadastroChavePixRepository.save(correntista);
//    return cadastroChavesPixEntity.getChavePix().getId();
//  }

  @Override
  public boolean existsByIdAndSituacaoChave(UUID id, SituacaoChave situacaoChave) {
    return chavePixRepository.existsByIdAndSituacaoChave(id, situacaoChave);
  }

  @Override
  public int countByIdCorrentistaAndNumeroAgenciaAndNumeroConta(Long idCorrentista, int numeroAgencia, long numeroConta) {
    return correntistaRepository.countByIdCorrentistaAndNumeroAgenciaAndNumeroConta(idCorrentista, numeroAgencia, numeroConta);
  }

  public UUID incluir(IncluirRequest incluirRequest) {
//    CorrentistaEntity correntista = new CorrentistaEntity(incluirRequest);
//    correntistaRepository.save(correntista);
    ChavePixEntity chavePix = new ChavePixEntity(incluirRequest.getPix());
    chavePixRepository.save(chavePix);
    return chavePix.getId();
  }

  public UUID incluir(CorrentistaEntity correntista, ChavePixEntity chavePix) {
    correntistaRepository.save(correntista);
    return chavePixRepository.save(chavePix).getId();
  }
}
