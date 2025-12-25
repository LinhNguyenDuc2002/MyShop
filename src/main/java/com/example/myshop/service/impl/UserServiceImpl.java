package com.example.myshop.service.impl;

import com.example.myshop.dto.UserDTO;
import com.example.myshop.dto.request.Credential;
import com.example.myshop.dto.request.UserRequest;
import com.example.myshop.entity.User;
import com.example.myshop.exception.I18nException;
import com.example.myshop.mapper.UserMapper;
import com.example.myshop.payload.UserPayload;
import com.example.myshop.repository.UserRepository;
import com.example.myshop.service.KeycloakService;
import com.example.myshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KeycloakService keycloakService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO create(UserPayload userPayload) throws Exception {
        boolean checkUsername = userRepository.existsByUsername(userPayload.getUsername());
        // early return
        if (checkUsername) {
            throw I18nException.builder()
                    .code(HttpStatus.CONFLICT)
                    .message("")
                    .build();
        }

        boolean checkEmail = userRepository.existsByEmail(userPayload.getEmail());
        if (checkEmail) {
            throw I18nException.builder()
                    .code(HttpStatus.CONFLICT)
                    .message("")
                    .build();
        }

        UserRequest userRequest = buildUserRequest(userPayload);
        String keycloakId = keycloakService.createUser(userRequest);
        User user = User.builder()
                .keycloakId(keycloakId)
                .username(userPayload.getUsername())
                .email(userPayload.getEmail())
                .firstName(userPayload.getFirstName())
                .lastName(userPayload.getLastName())
                .phoneNumber(userPayload.getPhoneNumber())
                .build();
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDTO getCurrentUser() {
        return null;
    }

    @Override
    public UserDTO get(String id) throws I18nException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    return I18nException.builder()
                            .code(HttpStatus.NOT_FOUND)
                            .message("")
                            .build();
                });

        return userMapper.toDto(user);
    }

    private UserRequest buildUserRequest(UserPayload userPayload) {
        return UserRequest.builder()
                .username(userPayload.getUsername())
                .enabled(true)
                .email(userPayload.getEmail())
                .firstName(userPayload.getFirstName())
                .lastName(userPayload.getLastName())
                .attributes(
                        UserRequest.Attribute.builder()
                                .phoneNumber(userPayload.getPhoneNumber())
                                .build()
                )
                .credentials(List.of(
                        new Credential("password", userPayload.getPassword())
                ))
                .build();
    }
}
