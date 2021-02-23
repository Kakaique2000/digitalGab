package br.com.gabweb.api.users

import br.com.gabweb.domain.Role
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import java.util.*

@Component
class UsuarioService(private val usuarioRepository: UsuarioRepository) : UserDetailsService {

  @Throws(UsernameNotFoundException::class)
  fun getLoggedUser(): br.com.gabweb.domain.Usuario {
    val principal = SecurityContextHolder.getContext().authentication.principal
    return if (principal is UserDetails) {
      usuarioRepository.findByEmail(principal.username).takeIf { it.isPresent }?.let {
        it.get()
      }?: throw UsernameNotFoundException(principal.username)
    } else {
      usuarioRepository.findByEmail(principal.toString()).takeIf { it.isPresent }?.let {
        it.get()
      }?: throw UsernameNotFoundException(principal.toString())
    }
  }

  @Throws(UsernameNotFoundException::class)
  override fun loadUserByUsername(s: String): UserDetails {
    val user = usuarioRepository.findByEmail(s)
      .orElseThrow { UsernameNotFoundException("The username $s doesn't exist") }

    val authorities = ArrayList<GrantedAuthority>()
    (user.roles!! as Iterable<Role>).forEach { authorities.add(SimpleGrantedAuthority(it.roleName)) }

    return User(
      user.email,
      user.password,
      authorities
    )
  }
}
