package com.servlet.example.http.dao;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import com.servlet.example.http.entity.User;
import com.servlet.example.http.util.ConnectionManager;

public class UserDao implements Dao<Integer, User> {

    private static final UserDao INSTANCE = new UserDao();

    private static final String SAVE = """
            insert into users (name, birthdate, email, password, role, gender)
            values (?,?,?,?,?,?)""";

    private UserDao() {}


    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public User save(User entity) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getBirthdate());
            preparedStatement.setObject(3, entity.getEmail());
            preparedStatement.setObject(4, entity.getPassword());
            preparedStatement.setObject(5, entity.getRole().name());
            preparedStatement.setObject(6, entity.getGender().name());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt("id"));
            }

            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
