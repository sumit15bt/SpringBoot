package com.demoSecurity.demoSpringSecurity.securitySpring;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.DefaultServerRedirectStrategy;
import org.springframework.security.web.server.ServerRedirectStrategy;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

public class SelfAuthenticationFailureHandler implements ServerAuthenticationFailureHandler {
    private static final String REDIRECT_URL = "/login";
    private final ServerRedirectStrategy redirectStrategy = new DefaultServerRedirectStrategy();

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange exchange, AuthenticationException ex) {
        ServerHttpRequest request = exchange.getExchange().getRequest();

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.addAll(request.getQueryParams());
        queryParams.add("error", ex.getMessage());

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(REDIRECT_URL)
                .queryParams(queryParams);
        return redirectStrategy.sendRedirect(exchange.getExchange(), URI.create(builder.toUriString()));
    }
}
