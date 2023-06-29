package com.servlet.example.http.entity;

import java.time.LocalDate;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    Integer id;
    String name;
    LocalDate birthdate;
    String email;
    String password;
    Role role;
    Gender gender;
}
