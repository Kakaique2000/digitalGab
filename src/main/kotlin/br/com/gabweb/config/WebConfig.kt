package br.com.gabweb.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


import br.com.gabweb.api.users.UsuarioService
import br.com.gabweb.security.JWTAuthenticationFilter
import br.com.gabweb.security.JWTAuthorizationFilter



@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebConfig(
  val bCryptPasswordEncoder: BCryptPasswordEncoder,
  val usuarioService: UsuarioService,
  val securityProperties: SecurityProperties
) : WebSecurityConfigurerAdapter() {
  override fun configure(http: HttpSecurity) {
    http
      .cors().and()
      .csrf().disable()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no sessions
      .and()
      .authorizeRequests()
      .antMatchers("/api/**").permitAll()
      .antMatchers("/error/**").permitAll()
      .antMatchers(HttpMethod.POST, "/login").permitAll()
      .anyRequest().authenticated()
      .and()
      .addFilter(JWTAuthenticationFilter(authenticationManager(), securityProperties))
      .addFilter(JWTAuthorizationFilter(authenticationManager(), securityProperties))
  }

  @Throws(Exception::class)
  override fun configure(auth: AuthenticationManagerBuilder) {
    auth.userDetailsService(usuarioService)
      .passwordEncoder(bCryptPasswordEncoder)
  }

  @Bean
  fun authProvider(): DaoAuthenticationProvider = DaoAuthenticationProvider().apply {
    setUserDetailsService(usuarioService)
    setPasswordEncoder(bCryptPasswordEncoder)
  }

  @Bean
  fun corsConfigurationSource(): CorsConfigurationSource = UrlBasedCorsConfigurationSource().also { cors ->
    CorsConfiguration().apply {
      allowedOrigins = listOf("*")
      allowedMethods = listOf("POST", "PUT", "DELETE", "GET", "OPTIONS", "HEAD")
      allowedHeaders = listOf(
        "Authorization",
        "Content-Type",
        "X-Requested-With",
        "Accept",
        "Origin",
        "Access-Control-Request-Method",
        "Access-Control-Request-Headers"
      )
      exposedHeaders = listOf(
        "Access-Control-Allow-Origin",
        "Access-Control-Allow-Credentials",
        "Authorization",
        "Content-Disposition"
      )
      maxAge = 3600
      cors.registerCorsConfiguration("/**", this)
    }
  }
}
