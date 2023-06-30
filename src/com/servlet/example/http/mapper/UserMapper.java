package com.servlet.example.http.mapper;

import com.servlet.example.http.dto.UserDto;
import com.servlet.example.http.entity.User;

public class UserMapper implements Mapper<User, UserDto> {

    private static final UserMapper INSTANCE = new UserMapper();

    private UserMapper() {
    }

    @Override
    public UserDto map(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .birthdate(user.getBirthdate())
                .email(user.getEmail())
                .role(user.getRole())
                .gender(user.getGender())
                .build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}
