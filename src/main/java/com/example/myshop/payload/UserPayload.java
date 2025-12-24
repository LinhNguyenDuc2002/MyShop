package com.example.myshop.payload;

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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserPayload {
    @NotBlank
    private String lastName;

    @NotBlank
    private String firstName;

    @NotBlank
    private String username;

    @Password
    private String password;

    @Email
    private String email;

    private String phoneNumber;
}
