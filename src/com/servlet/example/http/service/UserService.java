package com.servlet.example.http.service;

import java.util.Optional;

import com.servlet.example.http.dao.UserDao;
import com.servlet.example.http.dto.CreateUserDto;
import com.servlet.example.http.dto.UserDto;
import com.servlet.example.http.entity.User;
import com.servlet.example.http.exception.UserValidationException;
import com.servlet.example.http.mapper.CreateUserMapper;
import com.servlet.example.http.mapper.UserMapper;
import com.servlet.example.http.validator.UserValidator;
import com.servlet.example.http.validator.ValidationResult;

public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final UserDao userDao = UserDao.getInstance();
    private final UserValidator userValidator = UserValidator.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();

    private UserService() {}

    public static UserService getInstance() {
        return INSTANCE;
    }

    public Integer create(CreateUserDto dto) {
        ValidationResult validationResult = userValidator.isValid(dto);
        if (!validationResult.isValid()) {
            throw new UserValidationException(validationResult.getErrors());
        }
        User user = createUserMapper.map(dto);
        userDao.save(user);
        return user.getId();
    }

    public Optional<UserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password)
                .map(userMapper::map);
    }
}
