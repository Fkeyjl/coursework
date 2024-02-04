package com.exam.coursework.user.applicant.applicant_command;

import com.exam.coursework.shared.command.ActionCommand;
import com.exam.coursework.shared.web.Page;
import com.exam.coursework.user.User;
import com.exam.coursework.user.applicant.period.Period;
import com.exam.coursework.user.applicant.period.PeriodService;
import com.exam.coursework.utils.PathPageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.util.List;

public class PeriodCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(Logger.class);
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
            PeriodService periodService = new PeriodService();
            List<Period> periods = periodService.getAvailablePeriodsByUserId(user.getId());
            request.setAttribute("periods",periods);

        return new Page(PathPageManager.getProperty("page.period")).setDispatchType(Page.DispatchType.FORWARD);
    }
}
