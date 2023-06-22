package com.servlet.example.http.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import com.servlet.example.http.service.FlightService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/flights")
public class FlightServlet extends HttpServlet {

    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<h1>Список перелетов</h1>");
            writer.write("<ul>");
            flightService.findAll().forEach(dto -> writer.write(
                    """
                            <li>
                            <a href="/tickets?flightId=%d">%s</a>
                            </li>"""
                            .formatted(dto.getId(), dto.getDescription())
            ));
            writer.write("</ul>");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
