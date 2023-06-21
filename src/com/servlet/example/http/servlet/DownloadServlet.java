package com.servlet.example.http.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-disposition", "attachment;filename=\"test.txt\"");
        resp.setHeader("Content-type", "application/json");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (ServletOutputStream outputStream = resp.getOutputStream();
             InputStream stream = this.getClass().getClassLoader().getResourceAsStream("example.json")) {
            outputStream.write(stream.readAllBytes());
        }
    }
}
