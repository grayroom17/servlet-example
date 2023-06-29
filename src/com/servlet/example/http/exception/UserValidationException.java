package com.servlet.example.http.exception;

import java.util.List;

import com.servlet.example.http.validator.Error;
import lombok.Getter;

public class UserValidationException extends RuntimeException {
    @Getter
    private final List<Error> errors;

    public UserValidationException(List<Error> errors) {this.errors = errors;}
}
