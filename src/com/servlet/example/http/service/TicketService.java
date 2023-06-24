package com.servlet.example.http.service;

import java.util.List;

import com.servlet.example.http.dto.TicketDao;
import com.servlet.example.http.dto.TicketDto;

public class TicketService {
    private static final TicketService INSTANCE = new TicketService();

    private final TicketDao ticketDao = TicketDao.getInstance();

    private TicketService() {
    }

    public static TicketService getInstance() {
        return INSTANCE;
    }

    public List<TicketDto> findAllByFlightId(Long flightId) {
        return ticketDao.findAllByFlightId(flightId).stream()
                .map(ticket -> TicketDto.builder()
                        .id(ticket.getId())
                        .flightId(ticket.getFlightId())
                        .seatNo(ticket.getSeatNo())
                        .build()
                )
                .toList();
    }
}
