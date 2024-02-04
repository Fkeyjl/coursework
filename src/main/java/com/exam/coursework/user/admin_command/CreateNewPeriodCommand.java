package com.exam.coursework.user.admin_command;

import com.exam.coursework.shared.command.ActionCommand;
import com.exam.coursework.shared.web.Page;
import com.exam.coursework.user.applicant.period.Period;
import com.exam.coursework.user.applicant.period.PeriodService;
import com.exam.coursework.user.applicant.period.state.StateName;
import com.exam.coursework.utils.PathPageManager;
import com.exam.coursework.utils.ValidationManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;



public class CreateNewPeriodCommand implements ActionCommand {
    private final static Logger logger = Logger.getLogger(CreateNewPeriodCommand.class);

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        Page page = null;
        String name = request.getParameter("name");
        Period period = new Period();
        period.setName(name);
        PeriodService periodService = new PeriodService();

        if (ValidationManager.isValidate(period)) {
            periodService.createWithStateName(period, StateName.CHOICE_SUBJECTS);
            request.setAttribute("msg.success", true);
        } else request.setAttribute("error", ValidationManager.getFirsErrorMessage(period));


        page = new Page(PathPageManager.getProperty("page.create-period")).setDispatchType(Page.DispatchType.FORWARD);
        return page;
    }
}
