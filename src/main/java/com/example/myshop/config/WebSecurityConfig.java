package com.example.myshop.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.util.StringUtils;

@Configuration
@EnableWebSecurity
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Profile("!test")
public class WebSecurityConfig {
    // JSON Web Keys (JWKs) is a public key used to verify JWT's signature
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

    @Autowired
    private JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.disable()) //disable CORS
                .csrf(csrf -> csrf.disable()) //disable CSRF
                .httpBasic(httpBasic -> httpBasic.disable()) //disable HTTP Basic Authentication

                //handle and authenticate jwt received from request
                .oauth2ResourceServer(oauth2 -> oauth2
                        // customize how the server resolves bearer tokens from incoming requests
                        // This is particularly useful if you need to handle tokens in a non-standard way or source them from different locations (like headers, query parameters, ...)
                        // .bearerTokenResolver(tokenResolver())
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter))
                )
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new BasicAuthenticationEntryPoint()) //handle authentication exception
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler()) //handle access denied exception
                )
                .authorizeHttpRequests(authorize -> authorize //config authentication rules for requests
//                        .requestMatchers( HttpMethod.GET, "/product/**").permitAll()
//                        .requestMatchers( HttpMethod.GET, "/shop/**").permitAll()
//                        .requestMatchers( HttpMethod.GET, "/comment/**").permitAll()
//                        .requestMatchers( HttpMethod.GET, "/category/**").permitAll()
//                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
//                        .requestMatchers("/dashboard/**").permitAll()
                                .anyRequest().permitAll()
                );

        return http.build();
    }

    /**
     * Decode and authenticate JWT token
     *
     * @return
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuerUri);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuerUri);
        jwtDecoder.setJwtValidator(withIssuer);
        return jwtDecoder;
    }

//    /**
//     * Use lamda: request -> {...}
//     * @return
//     */
//    @Bean
//    public BearerTokenResolver tokenResolver() {
//        return request -> {
//            String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
//            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//                return bearerToken.substring(7);
//            }
//
//            return null;
//        };
//    }

//    @Bean
//    public BearerTokenResolver tokenResolver() {
//        return new BearerTokenResolver() {
//            @Override
//            public String resolve(HttpServletRequest request) {
//                String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
//                if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//                    return bearerToken.substring(7);
//                }
//                return null;
//            }
//        };
//    }
//
//    @Bean
//    public Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
//        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
//
//        //set JwtGrantedAuthoritiesConverter to export claims from JWT and convert them to a set of GrantedAuthorities.
//        jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthorityConverter);
//        return jwtConverter;
//    }
}