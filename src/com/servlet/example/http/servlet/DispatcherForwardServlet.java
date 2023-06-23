package com.servlet.example.http.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dispatcher-forward")
public class DispatcherForwardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/flights").forward(req, resp);

        //все что дальше не имеет смысла т.к. response будет возвращен из FlightServlet
        PrintWriter writer = resp.getWriter();
        writer.write("25");
    }
}
