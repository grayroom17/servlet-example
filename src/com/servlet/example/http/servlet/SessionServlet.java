package com.servlet.example.http.servlet;

import java.io.IOException;

import com.servlet.example.http.dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/sessions")
public
class SessionServlet extends HttpServlet {

    public static final String USER = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDto user = (UserDto) session.getAttribute(USER);
        if (user == null) {
            user = UserDto.builder()
                    .id(25L)
                    .mail("user@gmail.com")
                    .build();
            session.setAttribute(USER, user);
        }
    }
}
