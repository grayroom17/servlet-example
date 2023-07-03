package com.servlet.example.http.filter;

import java.io.IOException;
import java.util.Set;

import com.servlet.example.http.dto.UserDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.servlet.example.http.util.UrlPath.*;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Set<String> PUBLIC_PATH = Set.of(LOGIN, REGISTRATION, IMAGES, LOCALE);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String uri = ((HttpServletRequest) request).getRequestURI();
        if (isPublicPath(uri) || isUserLoggedIn(request)) {
            chain.doFilter(request, response);
        } else {
            String previousPage = ((HttpServletRequest) request).getHeader("referer");
            ((HttpServletResponse) response).sendRedirect(previousPage != null ? previousPage : LOGIN);
        }
    }

    private static boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream()
                .anyMatch(uri::startsWith);
    }

    private static boolean isUserLoggedIn(ServletRequest request) {
        UserDto user = (UserDto) ((HttpServletRequest) request).getSession().getAttribute("user");
        return user != null;
    }

}
