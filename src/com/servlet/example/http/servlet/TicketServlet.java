package com.servlet.example.http.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import com.servlet.example.http.service.TickerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {

    private final TickerService tickerService = TickerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long flightId = Long.valueOf(req.getParameter("flightId"));
        resp.setContentType("text/html");

        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<h1>Купленные билеты</h1>");
            writer.write("<ul>");
            tickerService.findAllByFlightId(flightId).forEach(dto ->
                    writer.write("<li>%s</li>".formatted(dto.getSeatNo())));
            writer.write("</ul>");
        }
    }
}
