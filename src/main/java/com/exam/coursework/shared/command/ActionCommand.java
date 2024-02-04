package com.exam.coursework.shared.command;


import com.exam.coursework.shared.web.Page;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public interface ActionCommand {
    Page execute(HttpServletRequest request, HttpServletResponse response);
}
