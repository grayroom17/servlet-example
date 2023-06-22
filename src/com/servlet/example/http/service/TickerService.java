package com.servlet.example.http.service;

import java.util.List;

import com.servlet.example.http.dto.TicketDao;
import com.servlet.example.http.dto.TicketDto;

public class TickerService {
    private static final TickerService INSTANCE = new TickerService();

    private final TicketDao ticketDao = TicketDao.getInstance();

    private TickerService() {
    }

    public static TickerService getInstance() {
        return INSTANCE;
    }

    public List<TicketDto> findAllByFlightId(Long flightId) {
        return ticketDao.findAllByFlightId(flightId).stream()
                .map(ticket -> new TicketDto(
                        ticket.getId(),
                        ticket.getFlightId(),
                        ticket.getSeatNo()
                ))
                .toList();
    }
}
