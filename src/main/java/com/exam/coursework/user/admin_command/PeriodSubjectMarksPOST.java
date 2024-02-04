package com.exam.coursework.user.admin_command;

import com.exam.coursework.shared.command.ActionCommand;
import com.exam.coursework.shared.web.Page;
import com.exam.coursework.user.applicant.ApplicantService;
import com.exam.coursework.user.applicant.ApplicantSubject;
import com.exam.coursework.user.applicant.period.state.StateName;
import com.exam.coursework.user.applicant.period.state.StateServiceSql;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class PeriodSubjectMarksPOST implements ActionCommand {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        int periodId = Integer.parseInt(request.getParameter("period"));
        int subject = Integer.parseInt(request.getParameter("subject"));
        ApplicantService applicantService = new ApplicantService();
        List<ApplicantSubject> applicantSubjectList = applicantService.getByPeriodAndSubject(periodId, subject);

        String currentMark;
        for (int i = 0; i < applicantSubjectList.size(); i++) {
            currentMark = request.getParameter("mark" + applicantSubjectList.get(i).getApplicant());
            if (currentMark.isEmpty())
                applicantSubjectList.get(i).setMark(null);
            else
                applicantSubjectList.get(i).setMark(Integer.parseInt(currentMark));
        }
        System.out.println(applicantSubjectList);
        StateServiceSql stateService = new StateServiceSql();
        StateName stateName = stateService.getByPeriodId(periodId);

        applicantService.updateMarks(applicantSubjectList);
        //request.setAttribute("applicants", applicantSubjectList);
        return new Page("/admin/period-subject-marks"+'?'+request.getQueryString()).setDispatchType(Page.DispatchType.REDIRECT);
    }
}
