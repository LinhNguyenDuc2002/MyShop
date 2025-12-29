package com.example.myshop.controller;

import com.example.myshop.dto.UserDTO;
import com.example.myshop.exception.I18nException;
import com.example.myshop.payload.UserPayload;
import com.example.myshop.projection.UserProjection;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/users")
public interface UserResource {
    @PostMapping
    ResponseEntity<UserDTO> register(@Valid @RequestBody UserPayload userPayload) throws Exception;

    @GetMapping("/me")
    ResponseEntity<UserProjection> getCurrentUser() throws I18nException;

    @GetMapping("/{id}")
    ResponseEntity<UserDTO> get(@PathVariable String id) throws I18nException;

    @PostMapping("/avatar")
    ResponseEntity<Void> setAvatar(MultipartFile avatar) throws I18nException;

    @PutMapping("/password")
    ResponseEntity<UserProjection> changePassword() throws I18nException;
}
