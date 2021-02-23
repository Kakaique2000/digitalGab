package br.com.gabweb.api.users

import br.com.gabweb.domain.Usuario
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/usuarios")
class UsuarioController(private val usuarioService: UsuarioService) {

  @GetMapping("/me")
  fun getLoggedUser(): Usuario {
    return usuarioService.getLoggedUser()
  }

}
