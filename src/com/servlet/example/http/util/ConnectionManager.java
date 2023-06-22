package com.servlet.example.http.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.servlet.example.http.util.PropertiesUtil.getPropertyValue;

public final class ConnectionManager {

    private static final String URL_KEY = "db.url";
    private static final String USER_KEY = "db.user";
    private static final String PASSWORD_KEY = "db.password";

    static {
        loadDriver();
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionManager() {}

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(getPropertyValue(URL_KEY),
                    getPropertyValue(USER_KEY),
                    getPropertyValue(PASSWORD_KEY));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
