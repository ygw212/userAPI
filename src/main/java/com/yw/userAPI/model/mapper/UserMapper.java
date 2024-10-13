package com.yw.userAPI.model.mapper;

import com.yw.userAPI.dto.UserDTO;
import com.yw.userAPI.model.User;

public class UserMapper {
    public User mapToModel(UserDTO userDTO){
        User user = User.builder()
                .userName(userDTO.getUserName())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .phoneNumber(userDTO.getPhoneNumber())
                .build();
        return user;
    }
}
