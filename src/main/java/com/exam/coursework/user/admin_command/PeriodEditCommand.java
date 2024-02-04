package com.exam.coursework.user.admin_command;

import com.exam.coursework.shared.command.ActionCommand;
import com.exam.coursework.shared.web.Page;
import com.exam.coursework.user.applicant.period.Period;
import com.exam.coursework.user.applicant.period.PeriodService;
import com.exam.coursework.utils.PathPageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class PeriodEditCommand implements ActionCommand {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        String idString = request.getParameter("id-period");
        int id = Integer.parseInt(idString);
        PeriodService periodService = new PeriodService();
        Period period = periodService.getByIdWithState(id);
        request.setAttribute("period",period);
        return new Page(PathPageManager.getProperty("page.period-edit")).setDispatchType(Page.DispatchType.FORWARD);
    }
}
