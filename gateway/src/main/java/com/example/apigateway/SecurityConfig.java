package com.example.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(
    ServerHttpSecurity http,
    MyAuthenticationFilter myAuthenticationFilter
  ) {
    http
      .csrf()
      .disable()
      .httpBasic()
      .securityContextRepository(
        new WebSessionServerSecurityContextRepository()
      )
      .and()
      .authorizeExchange()
      .pathMatchers("/**")
      .permitAll()
      .and()
      .addFilterAt(myAuthenticationFilter, SecurityWebFiltersOrder.FIRST);

    return http.build();
  }

  @Bean
  public MapReactiveUserDetailsService userDetailsService() {
    UserDetails user = User
      .withUsername("user1")
      .password("user1")
      .roles("USER1")
      .build();

    return new MapReactiveUserDetailsService(user);
  }
}
