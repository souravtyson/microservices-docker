package com.sourav.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityWebFilterChain(HttpSecurity http) {
        try {
            http.authorizeHttpRequests(req -> req
                            .requestMatchers("/","/public/**")
                            .permitAll()
                            .anyRequest()
                            .authenticated())
                    .oauth2ResourceServer(oauth2 -> oauth2
                            .jwt(jwt -> jwt.jwkSetUri("http://localhost:8080/realms/microservices-realm/protocol/openid-connect/certs")));
            return http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
