package com.servlet.example.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(urlPatterns = "/first-servlet")
public class FirstServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String header = headerNames.nextElement();
            System.out.println("%s: %s".formatted(header,req.getHeader(header)));
        }

        resp.setContentType("text/html; charset=UTF-8");
        resp.setHeader("token", "123456");
        try (PrintWriter writer = resp.getWriter()) {
            writer.write("Hello from First Servlet! Привет с первого сервлета!");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
