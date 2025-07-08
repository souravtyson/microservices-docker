package com.sourav.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${allowed.origins}")
    private String[] allowedOrigins;

    private UrlBasedCorsConfigurationSource corsConfigurationSource(String... origins) {
        final var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(origins));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    public SecurityFilterChain securityWebFilterChain(HttpSecurity http) {
        try {
            http.cors(cors ->
                    cors.configurationSource(corsConfigurationSource(allowedOrigins)));

            http.csrf(csrf -> csrf.disable());

            http.authorizeHttpRequests(req -> req
                            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                            .requestMatchers("/", "/public/**")
                            .permitAll()
                            .anyRequest()
                            .authenticated())
                    .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {
                    }));
            return http.build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @Bean
//    public ClientRegistrationRepository clientRepository() {
//
//        ClientRegistration keycloak = keycloakClientRegistration();
//        return new InMemoryClientRegistrationRepository(keycloak);
//    }
//
//    private ClientRegistration keycloakClientRegistration() {
//
//        return ClientRegistration.withRegistrationId("howtodoinjava-realm")
//                .clientId("employee-management-api")
//                .clientSecret("--generated--")
//                .redirectUri("http://localhost:9090/login/oauth2/code/employee-management-api")
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .issuerUri("http://localhost:8180/realms/howtodoinjava-realm")
//                .authorizationUri("http://localhost:8080/realms/howtodoinjava/protocol/openid-connect/auth")
//                .tokenUri("http://localhost:8180/realms/howtodoinjava/protocol/openid-connect/token")
//                .userInfoUri("http://localhost:8180/realms/howtodoinjava/protocol/openid-connect/userinfo")
//                .build();
//    }
//    @Component
//    @RequiredArgsConstructor
//    static class SpringAddonsJwtAuthenticationConverter implements Converter<Jwt, JwtAuthenticationToken> {
//
//        @Override
//        public JwtAuthenticationToken convert(Jwt jwt) {
//            final var authorities = new JwtGrantedAuthoritiesConverter().convert(jwt);
//            final String username = JsonPath.read(jwt.getClaims(), "preferred_username");
//            return new JwtAuthenticationToken(jwt, authorities, username);
//        }
//    }

//    @RequiredArgsConstructor
//    static class JwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<? extends GrantedAuthority>> {
//
//        @Override
//        @SuppressWarnings({ "rawtypes", "unchecked" })
//        public Collection<? extends GrantedAuthority> convert(Jwt jwt) {
//            return Stream.of("$.realm_access.roles", "$.resource_access.*.roles").flatMap(claimPaths -> {
//                        Object claim;
//                        try {
//                            claim = JsonPath.read(jwt.getClaims(), claimPaths);
//                        } catch (PathNotFoundException e) {
//                            claim = null;
//                        }
//                        if (claim == null) {
//                            return Stream.empty();
//                        }
//                        if (claim instanceof String claimStr) {
//                            return Stream.of(claimStr.split(","));
//                        }
//                        if (claim instanceof String[] claimArr) {
//                            return Stream.of(claimArr);
//                        }
//                        if (Collection.class.isAssignableFrom(claim.getClass())) {
//                            final var iter = ((Collection) claim).iterator();
//                            if (!iter.hasNext()) {
//                                return Stream.empty();
//                            }
//                            final var firstItem = iter.next();
//                            if (firstItem instanceof String) {
//                                return (Stream<String>) ((Collection) claim).stream();
//                            }
//                            if (Collection.class.isAssignableFrom(firstItem.getClass())) {
//                                return (Stream<String>) ((Collection) claim).stream().flatMap(colItem -> ((Collection) colItem).stream()).map(String.class::cast);
//                            }
//                        }
//                        return Stream.empty();
//                    })
//                    /* Insert some transformation here if you want to add a prefix like "ROLE_" or force upper-case authorities */
//
//                    .map(GrantedAuthority.class::cast).toList();
//        }
//    }

    //    @Bean
//    SecurityFilterChain filterChain(
//            HttpSecurity http,
//            Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter,
//            @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}") String issuerUri) throws Exception {
//
//        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)));
//
//        // Enable and configure CORS
////        http.cors(cors -> cors.configurationSource(corsConfigurationSource("http://localhost:4200")));
//
//        // State-less session (state in access-token only)
//        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        // Disable CSRF because of state-less session-management
//        http.csrf(csrf -> csrf.disable());
//
//        // Return 401 (unauthorized) instead of 302 (redirect to login) when
//        // authorization is missing or invalid
//        http.exceptionHandling(eh -> eh.authenticationEntryPoint((request, response, authException) -> {
////            response.addHeader(HttpHeaders.WWW_AUTHENTICATE, "OAuth realm=\"%s\"".formatted(issuerUri));
//            response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
//        }));
//
//        // @formatter:off
//        http.authorizeHttpRequests(accessManagement -> accessManagement
//                .requestMatchers("/actuator/health/readiness", "/actuator/health/liveness", "/v3/api-docs/**").permitAll()
//                .anyRequest().authenticated()
//        );
//        // @formatter:on
//
//        return http.build();
//    }
}
