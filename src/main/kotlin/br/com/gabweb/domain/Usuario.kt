package br.com.gabweb.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.Email

@Entity
@Table(name = "usuario")
class Usuario(
  @Id
  @GeneratedValue()
  val id: Int,

  @Column(name = "email")
  var email: String? = null,

  @Column(name = "password")
  var password: String? = null,

  @Column(name = "primeiro_nome")
  var primeiroNome: String? = null,

  @Column(name = "sobrenome")
  var sobrenome: String? = null,

  @JsonIgnore
  @OneToMany(mappedBy = "usuario")
  val solicitacoes: List<Solicitacao>? = null,


  @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
  @JoinTable(
    name = "user_role",
    joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
    inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
  )
  var roles: Set<Role>? = null
)

