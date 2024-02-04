package com.exam.coursework.security.command;


import com.exam.coursework.shared.web.Page;
import com.exam.coursework.utils.PathPageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class SignUpCommandTest {

    HttpServletResponse response;
    HttpServletRequest request;
    SignUpCommand signUpCommand;

    @Before
    public void setUp() throws Exception {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        signUpCommand = new SignUpCommand();
    }

    @Test
    public void execute() {
        Page expected = new Page(PathPageManager.getProperty("page.sign-up")).setDispatchType(Page.DispatchType.FORWARD);
        Assert.assertEquals(expected, signUpCommand.execute(request, response));
    }

    @After
    public void tearDown() throws Exception {
        response= null;
        request = null;
        signUpCommand = null;
    }
}