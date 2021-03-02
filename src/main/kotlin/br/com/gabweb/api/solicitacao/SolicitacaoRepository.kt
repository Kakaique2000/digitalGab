package br.com.gabweb.api.solicitacao

import br.com.gabweb.domain.STATUS_SOLICITACAO
import br.com.gabweb.domain.Solicitacao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SolicitacaoRepository: JpaRepository<Solicitacao, Long> {

  @Query(value = "SELECT new kotlin.Pair(s.status, COUNT(s.id)) from Solicitacao as s GROUP BY s.status")
  fun countGroupByStatus(): List<Pair<STATUS_SOLICITACAO, Long>>



}
