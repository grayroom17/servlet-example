package com.servlet.example.http.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.servlet.example.http.entity.Flight;
import com.servlet.example.http.entity.FlightStatus;
import com.servlet.example.http.util.ConnectionManager;

public class FlightDao implements Dao<Long, Flight> {

    private static final FlightDao INSTANCE = new FlightDao();

    private final static String FIND_ALL = """
            select *
            from flight""";

    private FlightDao() {}

    @Override
    public List<Flight> findAll() {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL))
        {
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Flight> flights = new ArrayList<>();

            while (resultSet.next()) {
                flights.add(buildFlight(resultSet));
            }

            return flights;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Flight buildFlight(ResultSet resultSet) throws SQLException {
        return new Flight(
                resultSet.getObject("id", Long.class),
                resultSet.getObject("flight_no", String.class),
                resultSet.getObject("departure_date", LocalDateTime.class),
                resultSet.getObject("departure_airport_code", String.class),
                resultSet.getObject("arrival_date", LocalDateTime.class),
                resultSet.getObject("arrival_airport_code", String.class),
                resultSet.getObject("aircraft_id", Integer.class),
                FlightStatus.valueOf(resultSet.getObject("status", String.class))
        );
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Flight entity) {

    }

    @Override
    public Flight save(Flight entity) {
        return null;
    }


    public static FlightDao getInstance() {
        return INSTANCE;
    }
}
