package com.servlet.example.http.servlet;

import java.io.IOException;
import java.util.List;

import com.servlet.example.http.dto.FlightDto;
import com.servlet.example.http.service.FlightService;
import com.servlet.example.http.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static java.util.stream.Collectors.toMap;

@WebServlet("/el")
public class ELServlet extends HttpServlet {

    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FlightDto> flights = flightService.findAll();
        req.setAttribute("flights", flights);
        req.getSession().setAttribute("flightsMap", flights.stream().collect(toMap(FlightDto::getId, FlightDto::getDescription)));
        req.getRequestDispatcher(JspHelper.getPath("el")).forward(req, resp);
    }

}
