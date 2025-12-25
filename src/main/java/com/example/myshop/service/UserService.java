package com.example.myshop.service;

import com.example.myshop.dto.UserDTO;
import com.example.myshop.exception.I18nException;
import com.example.myshop.payload.UserPayload;

public interface UserService {
    UserDTO create(UserPayload userPayload) throws Exception;

    UserDTO getCurrentUser();

    UserDTO get(String id) throws I18nException;
}
