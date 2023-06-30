package com.servlet.example.http.mapper;

import com.servlet.example.http.dto.CreateUserDto;
import com.servlet.example.http.entity.Gender;
import com.servlet.example.http.entity.Role;
import com.servlet.example.http.entity.User;
import com.servlet.example.http.util.LocalDateFormatter;

public class CreateUserMapper implements Mapper<CreateUserDto, User> {

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    private CreateUserMapper() {
    }

    @Override
    public User map(CreateUserDto dto) {
        return User.builder()
                .name(dto.getName())
                .birthdate(LocalDateFormatter.format(dto.getBirthdate()))
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(Role.valueOf(dto.getRole()))
                .gender(Gender.valueOf(dto.getGender()))
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
