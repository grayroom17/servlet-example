package com.servlet.example.http.servlet;

import java.io.IOException;

import com.servlet.example.http.dto.UserDto;
import com.servlet.example.http.service.UserService;
import com.servlet.example.http.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import static com.servlet.example.http.util.UrlPath.LOGIN;

@WebServlet(LOGIN)
public class LoginServlet extends HttpServlet {

    private static final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("login")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        userService.login(email, password)
                .ifPresentOrElse(
                        dto -> onLoginSuccess(req, resp, dto),
                        () -> onLoginFail(req, resp)
                );
    }

    @SneakyThrows
    private static void onLoginSuccess(HttpServletRequest req, HttpServletResponse resp, UserDto dto) {
        req.getSession().setAttribute("user", dto);
        resp.sendRedirect("/jstl-flights");
    }

    @SneakyThrows
    private static void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/login?error&email=" + req.getParameter("email"));
    }
}
