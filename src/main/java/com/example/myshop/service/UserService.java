package com.example.myshop.service;

import com.example.myshop.dto.UserDTO;
import com.example.myshop.exception.I18nException;
import com.example.myshop.payload.UserPayload;
import com.example.myshop.projection.UserProjection;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserDTO create(UserPayload userPayload) throws Exception;

    UserProjection getCurrentUser() throws I18nException;

    UserDTO get(String id) throws I18nException;

    void setAvatar(MultipartFile avatar) throws I18nException;
}
