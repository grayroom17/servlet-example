package com.servlet.example.http.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserDto {
    String name;
    String birthdate;
    String email;
    String password;
    String role;
    String gender;
}
