package com.itau.pix.infrastructure.repository.consulta;

import com.itau.pix.application.error.PixEntityNotFoundException;
import com.itau.pix.application.operacoes.consulta.dto.CadastroChavesPixDTO;
import com.itau.pix.application.operacoes.consulta.dto.ChavePixDTO;
import com.itau.pix.application.operacoes.consulta.dto.CorrentistaDTO;
import com.itau.pix.domain.enums.TipoChave;
import com.itau.pix.infrastructure.entity.CadastroChavesPixEntity;
import com.itau.pix.infrastructure.entity.ChavePixEntity;
import com.itau.pix.infrastructure.entity.CorrentistaEntity;
import com.itau.pix.infrastructure.repository.CadastroChavePixRepository;
import com.itau.pix.infrastructure.repository.ChavePixRepository;
import com.itau.pix.infrastructure.repository.CorrentistaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class ConsultaStorageService implements ConsultaStorage {


  private final ChavePixRepository chavePixRepository;
  private final CorrentistaRepository correntistaRepository;
  private final CadastroChavePixRepository cadastroChavePixRepository;


  @Override
  public Page<CadastroChavesPixDTO> consultarId(UUID id) {
    try {
      ChavePixEntity chavePixEntity = chavePixRepository.getReferenceById(id);
      CadastroChavesPixEntity cadastroChavesPix = cadastroChavePixRepository.findByIdChavePix(id);
      CorrentistaEntity correntistaEntity = correntistaRepository.getReferenceById(cadastroChavesPix.getIdCorrentista());

      CadastroChavesPixDTO cadastroChavesPixDTO = criarObjetoBuilder(chavePixEntity, correntistaEntity);
      return new PageImpl<>(List.of(cadastroChavesPixDTO), PageRequest.of(0, 0), 0);
    } catch (EntityNotFoundException | NullPointerException e) {
      String message = "chave não localizada";
      log.error("{} {}", message, id);
      throw new PixEntityNotFoundException(message);
    }
  }


  @Override
  public void consultarFiltroCombinado(String nomeCorrentista, String tipoChave, String agencia,
                                       String conta, String quantidade, String paginacao) {

    if (!tipoChave.isBlank()) {
      int page = Integer.parseInt(paginacao);
      int size = Integer.parseInt(quantidade);

      List<ChavePixEntity> allByTipoChave =
        chavePixRepository.findAllByTipoChave(TipoChave.fromString(tipoChave), PageRequest.of(page, size));
    }

  }

  private CadastroChavesPixDTO criarObjetoBuilder(ChavePixEntity pixEntity, CorrentistaEntity correntistaEntity) {
    ChavePixDTO chavePixDTO = new ChavePixDTO(pixEntity.getId(), pixEntity.getTipoChave(), pixEntity.getValorChave(),
      pixEntity.getDataHoraInclusaoDaChave(), pixEntity.getDataHoraInativacaoDaChave());

    CorrentistaDTO correntistaDTO =
      new CorrentistaDTO(String.valueOf(correntistaEntity.getIdCorrentista()), correntistaEntity.getTipoConta(),
        correntistaEntity.getNumeroAgencia(), correntistaEntity.getNumeroConta(),
        correntistaEntity.getNomeCorrentista(), correntistaEntity.getSobrenomeCorrentista());

    return new CadastroChavesPixDTO(correntistaDTO, chavePixDTO);
  }

}
