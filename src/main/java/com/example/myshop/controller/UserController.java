package com.example.myshop.controller;

import com.example.myshop.dto.UserDTO;
import com.example.myshop.exception.I18nException;
import com.example.myshop.payload.UserPayload;
import com.example.myshop.projection.UserProjection;
import com.example.myshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UserController implements UserResource {
    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<UserDTO> register(UserPayload userPayload) throws Exception {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.create(userPayload));
    }

    @Override
    public ResponseEntity<UserProjection> getCurrentUser() throws I18nException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getCurrentUser());
    }

    @Override
    public ResponseEntity<UserDTO> get(String id) throws I18nException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.get(id));
    }

    @Override
    public ResponseEntity<Void> setAvatar(MultipartFile avatar) throws I18nException, IOException {
        userService.setAvatar(avatar);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UserProjection> changePassword() throws I18nException {
        return null;
    }
}
