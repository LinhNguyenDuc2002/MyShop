package com.example.myshop.dto.request;

import com.example.myshop.annotation.password.Password;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

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
