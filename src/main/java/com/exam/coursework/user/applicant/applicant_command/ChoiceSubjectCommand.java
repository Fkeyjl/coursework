package com.exam.coursework.user.applicant.applicant_command;

import com.exam.coursework.shared.command.ActionCommand;
import com.exam.coursework.shared.web.Page;
import com.exam.coursework.user.applicant.ApplicantService;
import com.exam.coursework.user.applicant.period.state.StateName;
import com.exam.coursework.user.applicant.period.state.StateServiceSql;
import com.exam.coursework.user.subject.Subject;
import com.exam.coursework.user.subject.SubjectServiceSql;
import com.exam.coursework.utils.PathPageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.util.List;

public class ChoiceSubjectCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(SubjectServiceSql.class);

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        int id = (Integer) request.getSession().getAttribute("applicantId");

        ApplicantService applicantService = new ApplicantService();
        StateServiceSql stateService = new StateServiceSql();
        StateName stateName = stateService.getByPeriodId(applicantService.getById(id).getPeriod());
        if (!stateName.equals(StateName.CHOICE_SUBJECTS)) {
            request.setAttribute("access", false);
        } else
            request.setAttribute("access", true);


        SubjectServiceSql subjectService = new SubjectServiceSql();
        List<Subject> subjects = subjectService.getAll();
        List<Subject> selectedSubjects = subjectService.getSubjectByApplicantId(id);
        subjects.removeAll(selectedSubjects);
        request.setAttribute("subjects", subjects);
        request.setAttribute("selectedSubjects", selectedSubjects);

        return new Page(PathPageManager.getProperty("page.choice-subjects")).setDispatchType(Page.DispatchType.FORWARD);
    }
}
