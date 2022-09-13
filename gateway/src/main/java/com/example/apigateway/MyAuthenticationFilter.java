package com.example.apigateway;

import java.util.List;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class MyAuthenticationFilter implements WebFilter {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    System.out.println("***** New request *****");

    // print current session id
    System.out.println(exchange.getRequest().getCookies().toSingleValueMap());

    exchange
      .getSession()
      .map(
        session -> { // <- not executed in debug mode
          session.getAttributes().put("userId", "id123");

          System.out.println("userId in session: " + session.getAttribute("userId"));
          return session;
        }
      );

    return chain.filter(exchange);
  }
}
