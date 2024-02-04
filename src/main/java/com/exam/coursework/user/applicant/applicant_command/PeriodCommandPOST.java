package com.exam.coursework.user.applicant.applicant_command;

import com.exam.coursework.shared.command.ActionCommand;
import com.exam.coursework.shared.web.Page;
import com.exam.coursework.user.User;
import com.exam.coursework.user.applicant.ApplicantService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class PeriodCommandPOST implements ActionCommand {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int periodId = Integer.parseInt(request.getParameter("id"));
        ApplicantService applicantDAOSql = new ApplicantService();
        int applicantId = applicantDAOSql.getApplicantIdByUserIdAndPeriod(user, periodId);
        if (applicantId == -1 && applicantDAOSql.createNewByPeriodIdUserId(periodId, user.getId())) {
            applicantId = applicantDAOSql.getApplicantIdByUserIdAndPeriod(user, periodId);
        }
        session.setAttribute("applicantId", applicantId);
        Page page = new Page( "/applicant/info").setDispatchType(Page.DispatchType.REDIRECT);
        return page;
    }
}
