package br.com.gabweb.domain.form

import br.com.gabweb.domain.STATUS_SOLICITACAO
import javax.validation.constraints.NotEmpty

class SolicitacaoForm(
  @NotEmpty
  val status: STATUS_SOLICITACAO
) {
}
