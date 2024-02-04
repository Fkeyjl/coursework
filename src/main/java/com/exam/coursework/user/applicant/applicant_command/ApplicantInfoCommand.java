package com.exam.coursework.user.applicant.applicant_command;

import com.exam.coursework.shared.command.ActionCommand;
import com.exam.coursework.shared.web.Page;
import com.exam.coursework.user.applicant.ApplicantSubject;
import com.exam.coursework.user.applicant.status.Status;
import com.exam.coursework.user.applicant.status.StatusService;
import com.exam.coursework.user.speciality.Speciality;
import com.exam.coursework.user.speciality.SpecialityService;
import com.exam.coursework.user.subject.SubjectServiceSql;
import com.exam.coursework.utils.PathPageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class ApplicantInfoCommand implements ActionCommand {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        int id = (Integer) request.getSession().getAttribute("applicantId");
        SubjectServiceSql subjectService = new SubjectServiceSql();
        List<ApplicantSubject> subjects = subjectService.getByApplicantId(id);
        request.setAttribute("subjects", subjects);
        StatusService statusService = new StatusService();
        Status status = statusService.getByApplicantId(id);
        request.setAttribute( "status",status);
        SpecialityService specialityService= new SpecialityService();
        Speciality speciality = specialityService.getByApplicantId(id);
        request.setAttribute("speciality",speciality);
        return new Page(PathPageManager.getProperty("page.applicant-info")).setDispatchType(Page.DispatchType.FORWARD);
    }
}
