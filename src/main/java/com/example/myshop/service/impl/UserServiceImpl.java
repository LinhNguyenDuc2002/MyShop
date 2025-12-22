package com.example.myshop.service.impl;

import com.example.myshop.dto.UserDTO;
import com.example.myshop.dto.request.UserRequest;
import com.example.myshop.entity.User;
import com.example.myshop.exception.I18nException;
import com.example.myshop.payload.UserPayload;
import com.example.myshop.repository.UserRepository;
import com.example.myshop.service.KeycloakService;
import com.example.myshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KeycloakService keycloakService;

    @Override
    public UserDTO create(UserPayload userPayload) throws Exception {
        boolean checkUsername = userRepository.existsByUsername(userPayload.getUsername());
        // early return
        if(checkUsername) {
            throw I18nException.builder()
                    .code(HttpStatus.CONFLICT)
                    .message("")
                    .build();
        }

        boolean checkEmail = userRepository.existsByEmail(userPayload.getEmail());
        if(checkEmail) {
            throw I18nException.builder()
                    .code(HttpStatus.CONFLICT)
                    .message("")
                    .build();
        }

        UserRequest userRequest = buildUserRequest(userPayload);
        keycloakService.createUser(userRequest);
        User user = User.builder()
                .username(userPayload.getUsername())
                .email(userPayload.getEmail())
                .firstName(userPayload.getFirstName())
                .lastName(userPayload.getLastName())
                .phoneNumber(userPayload.getPhoneNumber())
                .build();
        return null;
    }

    private UserRequest buildUserRequest(UserPayload userPayload) {
        return UserRequest.builder()
                .username(userPayload.getUsername())
                .email(userPayload.getEmail())
                .firstName(userPayload.getFirstName())
                .lastName(userPayload.getLastName())
                .phoneNumber(userPayload.getPhoneNumber())
                .build();
    }
}
