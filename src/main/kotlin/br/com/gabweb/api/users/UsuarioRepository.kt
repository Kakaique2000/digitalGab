package br.com.gabweb.api.users

import org.springframework.data.repository.CrudRepository
import br.com.gabweb.domain.Usuario
import java.util.*

interface UsuarioRepository : CrudRepository<Usuario, Long> {
  fun findByEmail(username: String): Optional<Usuario>
}
