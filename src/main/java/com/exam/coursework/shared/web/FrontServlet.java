package com.exam.coursework.shared.web;


import com.exam.coursework.shared.command.ActionCommand;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "frontServlet", urlPatterns = {"/login/*", "/admin/*", "/applicant/*", "/period"})
public class FrontServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * This processRequest method is invoked from both
     * the servlet doGet and doPost methods
     **/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Page resultPage = null;
        CommandFactory commandFactory = new CommandFactory(request);
        ActionCommand command = commandFactory.getCommand();
        resultPage = command.execute(request, response);
        if (resultPage == null) {
            //TODO ERROR PAGE
            resultPage = new Page("/index.jsp").setDispatchType(Page.DispatchType.FORWARD);
            dispatch(request, response, resultPage.getName());
        } else if (resultPage.getDispatchType().equals(Page.DispatchType.REDIRECT))
            response.sendRedirect(request.getContextPath()+ resultPage.getName());
        else
            dispatch(request, response, resultPage.getName());
    }

    protected void dispatch(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
