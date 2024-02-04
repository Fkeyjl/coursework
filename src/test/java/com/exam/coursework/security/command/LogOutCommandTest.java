package com.exam.coursework.security.command;

import com.exam.coursework.shared.web.Page;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import static org.mockito.Mockito.*;

public class LogOutCommandTest {

    HttpServletResponse response;
    HttpServletRequest request;
    LogOutCommand logOutCommand;

    @Before
    public void setUp() throws Exception {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        logOutCommand = new LogOutCommand();
    }

    @After
    public void tearDown() throws Exception {
        request = null;
        response = null;
        logOutCommand = null;
    }

    @Test
    public void execute() {
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        Page expected = new Page(Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.REDIRECT);
        Assert.assertEquals(expected, logOutCommand.execute(request, response));
        verify(request,times(1)).getSession();
        verify(session,times(1)).invalidate();
    }
}