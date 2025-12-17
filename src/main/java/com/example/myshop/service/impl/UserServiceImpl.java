package com.example.myshop.service.impl;

import com.example.myshop.dto.UserDTO;
import com.example.myshop.payload.UserPayload;
import com.example.myshop.repository.UserRepository;
import com.example.myshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO create(UserPayload userPayload) {
        return null;
    }
}
