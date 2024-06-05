package com.itau.pix.infrastructure.repository;

import com.itau.pix.infrastructure.entity.CorrentistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CorrentistaRepository extends JpaRepository<CorrentistaEntity, Long> {




  int countByIdCorrentistaAndNumeroAgenciaAndNumeroConta(Long idCorrentista,
                                                         int numeroAgencia,
                                                         long numeroConta);


  //  PixModel updateByIdAndSituacaoChaveAndDataHoraInativacaoDaChave(String id, SituacaoChave situacaoChave, Timestamp dataHoraInativacaoDaChave);
//
//  @Query("SELECT p FROM PixModel p WHERE " +
//    "(:tipoChave is null or p.tipoChave = :tipoChave) and " +
//    "(:numeroAgencia is null or p.numeroAgencia = :numeroAgencia) and " +
//    "(:numeroConta is null or p.numeroConta = :numeroConta) and " +
//    "(:nomeCorrentista is null or p.nomeCorrentista = :nomeCorrentista) and " +
//    "(:dataCriacaoChave is null or p.dataHoraInclusaoDaChave = :dataCriacaoChave) and " +
//    "(:dataInativacaoChave is null or p.dataHoraInativacaoDaChave = :dataInativacaoChave)")
//  List<PixModel> buscarChavesDeacordoFiltro(@Param("tipoChave") TipoChave tipoChave,
//                                       @Param("numeroAgencia") String numeroAgencia,
//                                       @Param("numeroConta") String numeroConta,
//                                       @Param("nomeCorrentista") String nomeCorrentista,
//                                       @Param("dataCriacaoChave") OffsetDateTime dataCriacaoChave,
//                                       @Param("dataInativacaoChave") OffsetDateTime dataInativacaoChave);


}
