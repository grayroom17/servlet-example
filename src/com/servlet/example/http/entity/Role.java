package com.servlet.example.http.entity;

import java.util.Optional;
import java.util.stream.Stream;

public enum Role {
    ADMIN,
    USER;

    public static Optional<Role> find(String role) {
        return Stream.of(values())
                .filter(value -> value.name().equals(role))
                .findFirst();
    }
}
