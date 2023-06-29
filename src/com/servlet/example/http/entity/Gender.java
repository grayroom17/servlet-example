package com.servlet.example.http.entity;

import java.util.Optional;
import java.util.stream.Stream;

public enum Gender {
    MALE,
    FEMALE;

    public static Optional<Gender> find(String gender) {
        return Stream.of(values())
                .filter(value -> value.name().equals(gender))
                .findFirst();
    }
}
