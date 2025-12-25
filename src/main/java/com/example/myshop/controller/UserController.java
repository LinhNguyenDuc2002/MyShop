package com.example.myshop.controller;

import com.example.myshop.dto.UserDTO;
import com.example.myshop.payload.UserPayload;
import com.example.myshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<UserDTO> getCurrentUser() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getCurrentUser());
    }

    @Override
    public ResponseEntity<UserDTO> get(String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.get(id));
    }
}
