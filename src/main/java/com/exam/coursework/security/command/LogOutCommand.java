package com.exam.coursework.security.command;


import com.exam.coursework.shared.command.ActionCommand;
import com.exam.coursework.shared.web.Page;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class LogOutCommand implements ActionCommand {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        Page page = new Page( Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        return page;
    }
}
