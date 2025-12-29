package com.example.myshop.service;

import com.example.myshop.dto.request.UserRequest;

public interface KeycloakService {
    String createUser(UserRequest userRequest) throws Exception;

    void resetPassword();
}
