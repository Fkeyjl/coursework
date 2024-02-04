package com.exam.coursework.user.applicant.applicant_command;

import com.exam.coursework.shared.web.Page;
import com.exam.coursework.user.User;
import com.exam.coursework.user.applicant.period.PeriodService;
import com.exam.coursework.utils.PathPageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.mockito.Mockito.when;

@Ignore
public class PeriodCommandTest {
    @Mock
    PeriodService periodService;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;

    @InjectMocks
    PeriodCommand periodCommand;

    @Before
    public void init_mocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void execute() {
        User user = new User();
        user.setId(5);
        when(request.getSession().getAttribute("user")).thenReturn(user);
        when(periodService.getAvailablePeriodsByUserId(user.getId()));

        Assert.assertEquals( new Page(PathPageManager.getProperty("page.period")).setDispatchType(Page.DispatchType.FORWARD),periodCommand.execute(request,response));
    }
}