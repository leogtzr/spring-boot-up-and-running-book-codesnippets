package com.person.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class Config {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(final ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .pathMatchers("/persons/**").hasAuthority("SCOPE_openidLeo")
                 .pathMatchers("/persons/**").hasAuthority("SCOPE_openidLeo")
                .and().oauth2ResourceServer().jwt();

        return http.build();
    }
}
