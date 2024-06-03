package com.itau.pix.infrastructure.repository.inativacao;

import com.itau.pix.application.error.PixEntityNotFoundException;
import com.itau.pix.application.operacoes.inativacao.dto.CadastroChavesPixDTO;
import com.itau.pix.application.operacoes.inativacao.dto.ChavePixDTO;
import com.itau.pix.application.operacoes.inativacao.dto.CorrentistaDTO;
import com.itau.pix.infrastructure.entity.ChavePixEntity;
import com.itau.pix.infrastructure.entity.CorrentistaEntity;
import com.itau.pix.infrastructure.repository.ChavePixRepository;
import com.itau.pix.infrastructure.repository.CorrentistaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import static com.itau.pix.domain.enums.SituacaoChave.ATIVA;
import static com.itau.pix.domain.enums.SituacaoChave.INATIVA;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class InativacaoStorageService implements InativacaoStorage {

  private final ChavePixRepository chavePixRepository;
  private final CorrentistaRepository correntistaRepository;

  @Override
  public boolean existeChaveAtiva(UUID uuid, String idCorrentista) {
    //TODO add o IdCorrentista na busca da chave ativa
    return chavePixRepository.existsByIdAndSituacaoChave(uuid, ATIVA);
  }

  @Override
  public CadastroChavesPixDTO inativar(UUID uuid, String idCorrentista) {
    try {

      ChavePixRepository pixRepository = chavePixRepository;
      ChavePixEntity chavePixEntity = pixRepository.getReferenceById(uuid);

      Timestamp dataHoraInativacaoDaChave = Timestamp.from(new Date().toInstant());
      chavePixEntity.setDataHoraInativacaoDaChave(dataHoraInativacaoDaChave);
      chavePixEntity.setSituacaoChave(INATIVA);
      ChavePixEntity pixEntity = pixRepository.save(chavePixEntity);

      CorrentistaEntity correntistaEntity = correntistaRepository.getReferenceById(idCorrentista);
      return criarObjetoBuilder(pixEntity, correntistaEntity);
    } catch (EntityNotFoundException | NullPointerException e) {
      String message = "chave não localizada";
      log.error("{} {}", message, uuid);
      throw new PixEntityNotFoundException(message);
    }
  }

  private CadastroChavesPixDTO criarObjetoBuilder(ChavePixEntity pixEntity, CorrentistaEntity correntistaEntity) {
    ChavePixDTO chavePixDTO = new ChavePixDTO(pixEntity.getId(), pixEntity.getTipoChave(), pixEntity.getValorChave(),
      pixEntity.getSituacaoChave(), pixEntity.getDataHoraInclusaoDaChave(), pixEntity.getDataHoraInativacaoDaChave());

    CorrentistaDTO correntistaDTO = new CorrentistaDTO(String.valueOf(correntistaEntity.getIdCorrentista()),
      correntistaEntity.getTipoConta(), correntistaEntity.getNumeroAgencia(), correntistaEntity.getNumeroConta(),
      correntistaEntity.getNomeCorrentista(), correntistaEntity.getSobrenomeCorrentista());

    return new CadastroChavesPixDTO(correntistaDTO, chavePixDTO);
  }
}
