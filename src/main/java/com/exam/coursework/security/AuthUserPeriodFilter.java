package com.exam.coursework.security;

import com.exam.coursework.user.User;
import com.exam.coursework.user.role.Role;
import com.exam.coursework.utils.PathPageManager;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(urlPatterns = {"/applicant/*"})
public class AuthUserPeriodFilter extends HttpFilter {

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        User user = (User) session.getAttribute("user");
        Integer applicantId = (Integer) session.getAttribute("applicantId");
        if (nonNull(session) && nonNull(user) && (user.getRoleEntity().equals(Role.USER)))
            if (applicantId == null) {
                ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/period");
            } else
                filterChain.doFilter(request, response);
        else {
            RequestDispatcher dispatcher = ((HttpServletRequest) request).
                    getSession().
                    getServletContext().
                    getRequestDispatcher(PathPageManager.
                            getProperty("path.page.sign-in"));
            dispatcher.forward(request, response);
        }
    }

}
