package com.example.myshop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Request body for send the request to keycloak
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRequest {
    private String username;

    private Boolean enabled;

    private String lastName;

    private String firstName;

    private String email;

    private List<Credential> credentials;

    private Attribute attributes;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Attribute implements Serializable {
        private String phoneNumber;
    }
}
