package com.exam.coursework.user.admin_command;

import com.exam.coursework.shared.command.ActionCommand;
import com.exam.coursework.shared.web.Page;
import com.exam.coursework.user.applicant.period.Period;
import com.exam.coursework.user.applicant.period.PeriodService;
import com.exam.coursework.utils.PathPageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.util.List;

public class ShowPeriodsCommand implements ActionCommand {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response){
        PeriodService periodService = new PeriodService();
        List<Period> periods = periodService.getAll();
        request.setAttribute("periods",periods);
        return new Page(PathPageManager.getProperty("page.periods")).setDispatchType(Page.DispatchType.FORWARD);
    }
}
