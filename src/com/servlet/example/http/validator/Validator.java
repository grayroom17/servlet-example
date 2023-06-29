package com.servlet.example.http.validator;

public interface Validator<T> {
    ValidationResult isValid(T object);
}
