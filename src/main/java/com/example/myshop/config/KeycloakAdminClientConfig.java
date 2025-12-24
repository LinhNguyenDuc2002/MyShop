package com.example.myshop.config;

import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring automatically configs resource server role to authenticate token:
 * security.oauth2.resourceserver.jwt.issuer-uri=${ISSUER_URI}
 *
 * To use Keycloak as the Admin client role, need to config.
 * Because Spring doesn't have an auto-configuration module
 * Read more about Keycloak Spring Boot Adapter to detail
 */
@Configuration
public class KeycloakAdminClientConfig {
    @Value("${keycloak.admin.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.admin.realm}")
    private String realm;

    @Value("${keycloak.admin.credentials.client-id}")
    private String clientId;

    @Value("${keycloak.admin.credentials.client-secret}")
    private String clientSecret;

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .resteasyClient(new ResteasyClientBuilderImpl().connectionPoolSize(10).build())
                .build();
    }
}
