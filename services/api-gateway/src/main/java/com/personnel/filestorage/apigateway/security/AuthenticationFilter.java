package com.personnel.filestorage.apigateway.security;

import com.personnel.filestorage.apigateway.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationFilter implements GlobalFilter {
    private final JwtService jwtService;
    private final RouteValidator routeValidator;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (routeValidator.isSecured(request)) {
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "Authorization header missing");
            }

            String token = Objects.requireNonNull(request.getHeaders()
                    .getFirst(HttpHeaders.AUTHORIZATION)).substring(7);
            String email = request.getHeaders().getFirst("userEmail");
            if (email == null) {
                return onError(exchange, "User email missing");
            }
            if (!jwtService.isTokenValid(token, email)) {
                return onError(exchange, "Invalid Token");
            }

            String userEmail = jwtService.extractEmail(token);

            // Add user info to headers
            ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                    .header("userEmail", userEmail)
                    .build();

            return chain.filter(exchange.mutate().request(modifiedRequest).build());
        }

        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err) {
        log.error(err);
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
