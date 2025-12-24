package com.example.myshop.mapper;

import com.example.myshop.dto.UserDTO;
import com.example.myshop.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractMapper<User, UserDTO> {
    @Override
    public Class<UserDTO> getDtoClass() {
        return UserDTO.class;
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }
}
