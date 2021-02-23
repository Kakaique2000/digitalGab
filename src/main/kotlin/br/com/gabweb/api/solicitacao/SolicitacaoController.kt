package br.com.gabweb.api.solicitacao

import br.com.gabweb.api.users.UsuarioService
import br.com.gabweb.domain.Solicitacao
import br.com.gabweb.domain.dto.SolicitacaoDTO
import br.com.gabweb.domain.dto.toDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/v1/solicitacoes")
class SolicitacaoController(private val solicitacaoRepository: SolicitacaoRepository,
                            private val usuarioService: UsuarioService) {

  @GetMapping
  @PreAuthorize("hasAnyAuthority('ADMIN_USER', 'OWNER_USER')")
  fun getSolicitacoes(page: Pageable): ResponseEntity<Page<SolicitacaoDTO>> {
    return ResponseEntity.ok(solicitacaoRepository.findAll(page).map { it.toDTO() })

  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('ADMIN_USER', 'OWNER_USER', 'STANDARD_USER')")
  fun getSolicitacao(@PathVariable id: Long): ResponseEntity<SolicitacaoDTO> {
    return ResponseEntity.of(solicitacaoRepository.findById(id).map { it.toDTO() })
  }


  @PostMapping
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  fun addSolicitacao(@RequestBody @Valid solicitacao: Solicitacao): SolicitacaoDTO {
    solicitacao.usuario = usuarioService.getLoggedUser()
    return solicitacaoRepository.save(solicitacao).toDTO()

  }

}
