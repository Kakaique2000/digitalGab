package br.com.gabweb.api.solicitacao

import br.com.gabweb.domain.Solicitacao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SolicitacaoRepository: JpaRepository<Solicitacao, Long> {
}
