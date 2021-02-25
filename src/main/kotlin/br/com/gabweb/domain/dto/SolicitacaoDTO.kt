package br.com.gabweb.domain.dto

import br.com.gabweb.domain.Solicitacao
import java.time.LocalDateTime

class SolicitacaoDTO(
  val id: Long? = null,
  val descricao: String?,
  var criadoEm: LocalDateTime?,
  var atualizadoEm: LocalDateTime?,
  val usuarioId: Int?,
  val status: String,
) {
  constructor(solicitacao: Solicitacao) : this(
    solicitacao.id,
    solicitacao.descricao,
    solicitacao.criadoEm,
    solicitacao.atualizadoEm,
    solicitacao.usuario?.id,
    solicitacao.status.name
  )

}

fun Solicitacao.toDTO(): SolicitacaoDTO {
  return SolicitacaoDTO(
    this.id,
    this.descricao,
    this.criadoEm,
    this.atualizadoEm,
    this.usuario?.id,
    this.status.name
  )
}
