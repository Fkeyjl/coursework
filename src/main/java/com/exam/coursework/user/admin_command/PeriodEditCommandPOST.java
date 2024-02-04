package com.exam.coursework.user.admin_command;

import com.exam.coursework.shared.command.ActionCommand;
import com.exam.coursework.shared.web.Page;
import com.exam.coursework.user.applicant.period.Period;
import com.exam.coursework.user.applicant.period.PeriodService;
import com.exam.coursework.utils.PathPageManager;
import com.exam.coursework.utils.ValidationManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class PeriodEditCommandPOST implements ActionCommand {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        int state = Integer.parseInt(request.getParameter("state"));
        int id = Integer.parseInt(request.getParameter("id"));
        PeriodService periodService = new PeriodService();
        Period period = new Period();
        period.setName(name);
        period.setState(state);
        period.setId(id);
        if (request.getParameter("type").equals("state")) {
            periodService.setNextState(period);
        } else {
            if (!ValidationManager.isValidate(period)) {
                request.setAttribute("error", ValidationManager.getFirsErrorMessage(period));
                request.setAttribute("period",period);
                return new Page(PathPageManager.getProperty("page.period-edit")).setDispatchType(Page.DispatchType.FORWARD);
            } else
            periodService.update(period);
        }

        return new Page("/admin/period-edit"+'?'+request.getQueryString()).setDispatchType(Page.DispatchType.REDIRECT);
    }
}
