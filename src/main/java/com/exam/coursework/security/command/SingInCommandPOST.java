package com.exam.coursework.security.command;

import com.exam.coursework.shared.command.ActionCommand;
import com.exam.coursework.shared.service.ServiceLoader;
import com.exam.coursework.shared.web.Page;
import com.exam.coursework.user.User;
import com.exam.coursework.utils.PathPageManager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class SingInCommandPOST implements ActionCommand {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        return executeWithServiceLoader(request,response,new ServiceLoader());
    }
    Page executeWithServiceLoader(HttpServletRequest request, HttpServletResponse response, ServiceLoader serviceLoader){
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = serviceLoader.userService().findByLoginAndPassword(login, password);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            return new Page(Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        } else {
            setErrorMessages(request);
            return new Page(PathPageManager.getProperty("page.sign-in")).setDispatchType(Page.DispatchType.FORWARD);
        }
    }

    void setErrorMessages(HttpServletRequest request) {
        request.setAttribute("error",  true);
    }
}
