package com.example.myshop.service;

import com.example.myshop.dto.request.UserRequest;
import com.example.myshop.dto.response.UserRepresentation;

public interface KeycloakService {
    UserRepresentation createUser(UserRequest userRequest) throws Exception;
}
