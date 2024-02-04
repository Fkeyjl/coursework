package com.exam.coursework.security;

import com.exam.coursework.user.User;
import com.exam.coursework.user.role.Role;
import com.exam.coursework.utils.PathPageManager;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(filterName = "admin-security")
public class AuthAdminFilter extends HttpFilter {

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        User user = (User) session.getAttribute("user");
        if (nonNull(user) && user.getRoleEntity().equals(Role.ADMIN))
                filterChain.doFilter(request, response);
         else {
            RequestDispatcher dispatcher = ((HttpServletRequest) request).getSession()
                    .getServletContext()
                    .getRequestDispatcher(PathPageManager.getProperty("page.error404")); // вызов страницы ответа на запрос
            dispatcher.forward(request, response);
        }
    }
}
