package br.com.gabweb.domain

import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "solicitacao")
class Solicitacao(

  @Id @GeneratedValue()
  val id: Long? = null,

  @Column(name = "descricao")
  @Size(min = 10, max = 500)
  val descricao: String? = null,

  @Column(name = "criado_em")
  var criadoEm: LocalDateTime? = null,

  @Column(name = "atualizado_em")
  var atualizadoEm: LocalDateTime? = null,

  @Enumerated()
  @Column(name = "status")
  var status: STATUS_SOLICITACAO = STATUS_SOLICITACAO.ABERTA,

  @ManyToOne
  @JoinColumn(name = "usuario_id")
  var usuario: Usuario? = null,

  ) {

  @PrePersist
  fun onCreate() {
    criadoEm = LocalDateTime.now()
    atualizadoEm = LocalDateTime.now()
  }

  @PreUpdate
  fun onUpdate() {
    atualizadoEm = LocalDateTime.now()
  }

}

enum class STATUS_SOLICITACAO {
  ABERTA,
  EM_ANDAMENTO,
  FINALIZADA,
  DESATIVADA
}
