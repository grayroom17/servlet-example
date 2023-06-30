package com.servlet.example.http.dto;

import java.time.LocalDate;

import com.servlet.example.http.entity.Gender;
import com.servlet.example.http.entity.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserDto {
    Integer id;
    String name;
    LocalDate birthdate;
    String email;
    Role role;
    Gender gender;
}
