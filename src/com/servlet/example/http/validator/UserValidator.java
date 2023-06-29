package com.servlet.example.http.validator;

import com.servlet.example.http.dto.CreateUserDto;
import com.servlet.example.http.entity.Gender;
import com.servlet.example.http.entity.Role;
import com.servlet.example.http.util.LocalDateFormatter;

public class UserValidator implements Validator<CreateUserDto> {

    private static final UserValidator INSTANCE = new UserValidator();

    private UserValidator() {}

    @Override
    public ValidationResult isValid(CreateUserDto dto) {
        ValidationResult validationResult = new ValidationResult();
        if (Gender.find(dto.getGender()).isEmpty()) {
            validationResult.add(Error.of("invalid.gender", "Gender is not valid"));
        }
        if (Role.find(dto.getRole()).isEmpty()) {
            validationResult.add(Error.of("invalid.role", "Role is not valid"));
        }
        if (!LocalDateFormatter.isValid(dto.getBirthdate())) {
            validationResult.add(Error.of("invalid.birthdate", "Birthdate is not valid"));
        }
        return validationResult;
    }

    public static UserValidator getInstance() {
        return INSTANCE;
    }
}
