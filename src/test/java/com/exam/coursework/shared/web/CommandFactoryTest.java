package com.exam.coursework.shared.web;

import com.exam.coursework.exception.NotFoundCommandException;
import com.exam.coursework.shared.command.ActionCommand;
import com.exam.coursework.user.admin_command.AdminHomeCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommandFactoryTest {

    HttpServletRequest request;

    CommandFactory commandFactory;
    String actual;

    @Before
    public   void setUp()  {
        request =mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("example.com/admin");
        when(request.getContextPath()).thenReturn("example.com");
        when(request.getMethod()).thenReturn("GET");
        commandFactory = new CommandFactory(request);

    }

    @Test
    public void getCommand_correct() {
        when(request.getRequestURI()).thenReturn("example.com/admin");
        when(request.getContextPath()).thenReturn("example.com");
        when(request.getMethod()).thenReturn("GET");
        actual = commandFactory.getCommandName(ActionCommandEnum.class).trim();
        ActionCommand actionCommand = commandFactory.getCommand();
        Assert.assertTrue(actionCommand instanceof AdminHomeCommand);
    }

    @Test
    public void getCommandName_correct() {
        when(request.getRequestURI()).thenReturn("example.com/admin");
        when(request.getContextPath()).thenReturn("example.com");
        when(request.getMethod()).thenReturn("GET");
        actual = commandFactory.getCommandName(ActionCommandEnum.class);
        String actual = commandFactory.getCommandName(ActionCommandEnum.class);
        Assert.assertEquals("ADMIN_HOME",actual);
    }

    @Test(expected = NotFoundCommandException.class)
    public void getCommandName_wrongRequest_exception() {
        when(request.getRequestURI()).thenReturn("example.com/wrong");
        when(request.getContextPath()).thenReturn("example.com");
        when(request.getMethod()).thenReturn("GET");
        actual = commandFactory.getCommandName(ActionCommandEnum.class);
        String actual = commandFactory.getCommandName(ActionCommandEnum.class);
        Assert.assertEquals("ADMIN_HOME",actual);
    }
}