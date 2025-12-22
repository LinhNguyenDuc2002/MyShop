package com.example.myshop.service.impl;

import com.example.myshop.dto.request.UserRequest;
import com.example.myshop.dto.response.UserRepresentation;
import com.example.myshop.service.KeycloakService;
import com.example.myshop.webclient.WebClientProcessor;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

/**
 * https://www.keycloak.org/docs-api/latest/rest-api/index.html
 */
@Service
public class KeycloakServiceImpl implements KeycloakService {
    @Value("${keycloak.admin.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.admin.realm}")
    private String realm;

    @Autowired
    private WebClientProcessor webClient;

    @Autowired
    private Keycloak keycloak;

    /**
     * Format ex: http://<KEYCLOAK_URL>/admin/realms<REALM_NAME>/users
     */
    private static final String USER_RESOURCE = "%s/admin/realms/%s/users";

    @Override
    public UserRepresentation createUser(UserRequest userRequest) throws Exception {
        String endpoint = String.format(USER_RESOURCE, serverUrl, realm);

        Map<String, String> headers = Map.of(
                HttpHeaders.AUTHORIZATION, "Bearer " + keycloak.tokenManager().getAccessToken().getToken(),
                HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE,
                HttpHeaders.ACCEPT, Collections.singletonList(MediaType.APPLICATION_JSON_VALUE).toString()
        );

        Object response = webClient.post(
                endpoint,
                headers,
                userRequest,
                UserRepresentation.class
        );

//        log.info("Created new member with email: {}", member.getEmail());
//
//        if (responseEntity.getBody() != null) {
//            List<String> roles = ZenoUserRole.handleFromKeycloakRole(responseEntity.getBody().getOrgRoles());
//            responseEntity.getBody().setOrgRoles(roles);
//        }

        return null;
    }
}
