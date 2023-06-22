package com.servlet.example.http.service;

import java.util.List;

import com.servlet.example.http.dao.FlightDao;
import com.servlet.example.http.dto.FlightDto;

public class FlightService {

    private static final FlightService INSTANCE = new FlightService();

    private final FlightDao flightDao = FlightDao.getInstance();

    private FlightService() {}

    public static FlightService getInstance() {
        return INSTANCE;
    }

    public List<FlightDto> findAll() {
        return flightDao.findAll().stream()
                .map(flight -> new FlightDto(
                        flight.getId(),
                        " %s - %s - %s".formatted(
                                flight.getDepartureAirportCode(),
                                flight.getArrivalAirportCode(),
                                flight.getStatus()
                        )
                )).toList();
    }
}
