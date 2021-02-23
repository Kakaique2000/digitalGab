package br.com.gabweb.domain.dto

import br.com.gabweb.domain.Solicitacao
import java.time.LocalDateTime

class SolicitacaoDTO(
  val id: Long? = null,
  val descricao: String?,
  var criadoEm: LocalDateTime?,
  var atualizadoEm: LocalDateTime?,
  val usuarioId: Int?,
) {
  constructor(solicitacao: Solicitacao) : this(
    solicitacao.id,
    solicitacao.descricao,
    solicitacao.criadoEm,
    solicitacao.atualizadoEm,
    solicitacao.usuario?.id,
  )

}

fun Solicitacao.toDTO(): SolicitacaoDTO {
  return SolicitacaoDTO(
    this.id,
    this.descricao,
    this.criadoEm,
    this.atualizadoEm,
    this.usuario?.id,
  )
}
