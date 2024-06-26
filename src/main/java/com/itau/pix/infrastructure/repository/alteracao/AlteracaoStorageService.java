package com.itau.pix.infrastructure.repository.alteracao;

import com.itau.pix.application.error.PixEntityNotFoundException;
import com.itau.pix.application.operacoes.alteracao.dto.CadastroChavesPixDTO;
import com.itau.pix.application.operacoes.alteracao.dto.ChavePixDTO;
import com.itau.pix.application.operacoes.alteracao.dto.CorrentistaDTO;
import com.itau.pix.application.operacoes.alteracao.dto.CorrentistaRequestDTO;
import com.itau.pix.domain.enums.SituacaoChave;
import com.itau.pix.domain.enums.TipoConta;
import com.itau.pix.infrastructure.entity.ChavePixEntity;
import com.itau.pix.infrastructure.entity.CorrentistaEntity;
import com.itau.pix.infrastructure.repository.ChavePixRepository;
import com.itau.pix.infrastructure.repository.CorrentistaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class AlteracaoStorageService implements AlteracaoStorage {


  private final ChavePixRepository chavePixRepository;
  private final CorrentistaRepository correntistaRepository;

  @Override
  public CadastroChavesPixDTO alterar(CorrentistaRequestDTO correntista, String idCorrentista, UUID idChavePix) {
    try {

      CorrentistaRepository repository = correntistaRepository;
      CorrentistaEntity correntistaEntity = repository.getReferenceById(idCorrentista);
      correntistaEntity.setTipoConta(TipoConta.fromString(correntista.tipoConta()));
      correntistaEntity.setNumeroAgencia(correntista.numeroAgencia());
      correntistaEntity.setNumeroConta(correntista.numeroConta());
      correntistaEntity.setNomeCorrentista(correntista.nomeCorrentista());
      Optional.ofNullable(correntista.sobrenomeCorrentista())
        .ifPresent(correntistaEntity::setSobrenomeCorrentista);

      CorrentistaEntity correntistaAlterEntity = repository.save(correntistaEntity);
      ChavePixEntity chavePixEntity = chavePixRepository.getReferenceById(idChavePix);
      return criarObjetoBuilder(chavePixEntity, correntistaAlterEntity);
    } catch (EntityNotFoundException | NullPointerException e) {
      String message = "chave não localizada";
      log.error("{} {}", message, idChavePix);
      throw new PixEntityNotFoundException(message);
    }
  }

  @Override
  public boolean existsByIdAndSituacaoChave(UUID id, String idCorrentista, SituacaoChave situacaoChave) {
    return chavePixRepository.existsByIdAndSituacaoChave(id, situacaoChave);
  }

  private CadastroChavesPixDTO criarObjetoBuilder(ChavePixEntity pixEntity, CorrentistaEntity correntistaEntity) {
    ChavePixDTO chavePixDTO = new ChavePixDTO(pixEntity.getId(), pixEntity.getTipoChave(), pixEntity.getValorChave(),
      pixEntity.getDataHoraInclusaoDaChave());

    CorrentistaDTO correntistaDTO = new CorrentistaDTO(String.valueOf(correntistaEntity.getIdCorrentista()),
      correntistaEntity.getTipoConta(), correntistaEntity.getNumeroAgencia(), correntistaEntity.getNumeroConta(),
      correntistaEntity.getNomeCorrentista(), correntistaEntity.getSobrenomeCorrentista());

    return new CadastroChavesPixDTO(correntistaDTO, chavePixDTO);
  }
}
