package com.exam.coursework.security.command;

import com.exam.coursework.shared.command.ActionCommand;
import com.exam.coursework.shared.web.Page;
import com.exam.coursework.utils.PathPageManager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class SignUpCommand implements ActionCommand {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        return new Page( PathPageManager.getProperty("page.sign-up")).setDispatchType(Page.DispatchType.FORWARD);
    }
}
