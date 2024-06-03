package com.itau.pix;

import com.itau.pix.entity.Pix;
import com.itau.pix.enums.SituacaoChave;
import com.itau.pix.enums.TipoChave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface PixRepository extends JpaRepository<Pix, UUID> {


  int countByIdClienteAndNumeroAgenciaAndNumeroConta(String idCliente,
                                                     int numeroAgencia,
                                                     long numeroConta);


  boolean existsByIdAndSituacaoChave(UUID id, SituacaoChave situacaoChave);


  //  Pix updateByIdAndSituacaoChaveAndDataHoraInativacaoDaChave(String id, SituacaoChave situacaoChave, Timestamp dataHoraInativacaoDaChave);

  @Query("SELECT p FROM Pix p WHERE " +
    "(:tipoChave is null or p.tipoChave = :tipoChave) and " +
    "(:numeroAgencia is null or p.numeroAgencia = :numeroAgencia) and " +
    "(:numeroConta is null or p.numeroConta = :numeroConta) and " +
    "(:nomeCorrentista is null or p.nomeCorrentista = :nomeCorrentista) and " +
    "(:dataCriacaoChave is null or p.dataHoraInclusaoDaChave = :dataCriacaoChave) and " +
    "(:dataInativacaoChave is null or p.dataHoraInativacaoDaChave = :dataInativacaoChave)")
  List<Pix> buscarChavesDeacordoFiltro(@Param("tipoChave") TipoChave tipoChave,
                                       @Param("numeroAgencia") String numeroAgencia,
                                       @Param("numeroConta") String numeroConta,
                                       @Param("nomeCorrentista") String nomeCorrentista,
                                       @Param("dataCriacaoChave") OffsetDateTime dataCriacaoChave,
                                       @Param("dataInativacaoChave") OffsetDateTime dataInativacaoChave);


}
