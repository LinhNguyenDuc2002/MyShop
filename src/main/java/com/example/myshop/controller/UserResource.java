package com.example.myshop.controller;

import com.example.myshop.dto.UserDTO;
import com.example.myshop.payload.UserPayload;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
public interface UserResource {
    @PostMapping
    ResponseEntity<UserDTO> register(@Valid @RequestBody UserPayload userPayload) throws Exception;
}
