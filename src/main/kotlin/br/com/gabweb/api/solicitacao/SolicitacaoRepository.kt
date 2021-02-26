package br.com.gabweb.api.solicitacao

import br.com.gabweb.domain.STATUS_SOLICITACAO
import br.com.gabweb.domain.Solicitacao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SolicitacaoRepository: JpaRepository<Solicitacao, Long> {
  fun countAllByStatus(status: STATUS_SOLICITACAO): Int
}
