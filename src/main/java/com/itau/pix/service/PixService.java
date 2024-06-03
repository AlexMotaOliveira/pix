package com.itau.pix.service;

import com.itau.pix.PixRepository;
import com.itau.pix.entity.Pix;
import com.itau.pix.enums.SituacaoChave;
import com.itau.pix.enums.TipoChave;
import com.itau.pix.error.PixEntityNotFoundException;
import com.itau.pix.error.PixError;
import com.itau.pix.interfaces.PixInterface;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class PixService implements PixInterface {

  private final PixRepository pixRepository;

  @Override
  public UUID incluirChave(@Valid Pix pix) {
    PixRepository repository = pixRepository;
    if (repository.existsByIdAndSituacaoChave(pix.getId(), SituacaoChave.ATIVA)) {
      throw new PixError("Chave já cadastrada");
    }
    if (repository.countByIdClienteAndNumeroAgenciaAndNumeroConta(pix.getIdCliente(),
      pix.getNumeroAgencia(), pix.getNumeroConta()) > pix.getTipoCliente().getValorMaximoChaves()) {
      throw new PixError("Cliente já possui limete de chaves cadastradas");
    }
    return repository.save(pix).getId();
  }

  @Override
  public boolean validarChaveExistente(Pix pix) {
    return false;
  }

  @Override
  public boolean validarLimiteChaves(Pix pix) {
    return false;
  }


  @Override
  public Pix inativar(UUID id) {
    try {
      Pix pix = getReferenceById(id);
      if (pix.getSituacaoChave() == SituacaoChave.ATIVA) {
        Timestamp date = new Timestamp(new Date().getTime());
        pix.setSituacaoChave(SituacaoChave.INATIVA);
        pix.setDataHoraInativacaoDaChave(date);

        return pixRepository.save(pix);
      }
      throw new PixError("Chave já estava desativada");
    } catch (EntityNotFoundException e) {
      throw new PixError("chave não cadastrada");
    }
  }

  @Override
  public Pix alterarChave(Pix pix) {
    try {
      Pix pixEntity = getReferenceById(pix.getId());
      if (pixEntity.getSituacaoChave() == SituacaoChave.ATIVA) {
        pixEntity.setTipoConta(pix.getTipoConta());
        pixEntity.setNumeroConta(pix.getNumeroConta());
        pixEntity.setNumeroAgencia(pixEntity.getNumeroAgencia());
        pixEntity.setNomeCorrentista(pix.getNomeCorrentista());
        Optional.ofNullable(pix.getSobrenomeCorrentista())
          .ifPresent(pix::setSobrenomeCorrentista);
        return pixRepository.save(pixEntity);
      }
      throw new PixError("chave desativada, não é possivel fazer atualização");
    } catch (EntityNotFoundException e) {
      throw new PixEntityNotFoundException("chave não cadastrada");
    }
  }


  @Override
  public List<Pix> listarChaves(UUID id,
                                String tipoChave,
                                String agenciaConta,
                                String nomeCorrentista,
                                OffsetDateTime dataCriacaoChave,
                                OffsetDateTime dataInativacaoChave) {
    // buscar somente por Id
    if (id != null) {
      try {
        if (agenciaConta != null || nomeCorrentista != null || dataCriacaoChave != null || dataInativacaoChave != null) {
          throw new PixError("somente o id deve ser informado na pesquisa por id");
        }
        Pix pixEntity = getReferenceById(id);
        if (pixEntity.getSituacaoChave() == SituacaoChave.ATIVA) {
          return List.of(pixEntity);
        }
        throw new PixError("chave desativada, não é possivel fazer atualização");
      } catch (EntityNotFoundException e) {
        throw new PixEntityNotFoundException("chave não cadastrada");
      }
    } else {
      if (dataCriacaoChave != null && dataInativacaoChave != null) {
        throw new PixError("somente um dos campos deve ser preenchidos, dataCriacao ou dataInativacao");
      }

      String agencia = agenciaConta.substring(0, 4);
      String conta = agenciaConta.substring(5, 12);

      return pixRepository.buscarChavesDeacordoFiltro(TipoChave.fromString(tipoChave),
        agencia,
        conta,
        nomeCorrentista,
        dataCriacaoChave,
        dataInativacaoChave);
    }
  }

  @Override
  public Pix buscarId(UUID uuid) {
    return null;
  }

  private Pix getReferenceById(UUID id) {
    return pixRepository.getReferenceById(id);
  }


}
