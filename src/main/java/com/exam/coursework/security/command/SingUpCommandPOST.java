package com.exam.coursework.security.command;

import com.exam.coursework.security.RegistrationUtil;
import com.exam.coursework.shared.command.ActionCommand;
import com.exam.coursework.shared.web.Page;
import com.exam.coursework.user.User;
import com.exam.coursework.user.role.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SingUpCommandPOST implements ActionCommand {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        return new RegistrationUtil(request).registration(createUsingByRequest(request));
    }

    User createUsingByRequest(HttpServletRequest  request){
        User user = new User();
        user.setRoleEntity(Role.USER);
        user.setRole(2);
        user.setPassword(request.getParameter("password"));
        user.setFirstname( request.getParameter("firstname"));
        user.setEmail(request.getParameter("login"));
        user.setSurname(request.getParameter("surname"));
        return user;
    }
}
