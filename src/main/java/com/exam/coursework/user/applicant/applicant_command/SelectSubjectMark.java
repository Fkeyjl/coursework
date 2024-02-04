package com.exam.coursework.user.applicant.applicant_command;

import com.exam.coursework.shared.command.ActionCommand;
import com.exam.coursework.shared.web.Page;
import com.exam.coursework.user.subject.Subject;
import com.exam.coursework.user.subject.SubjectServiceSql;
import com.exam.coursework.utils.PathPageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class SelectSubjectMark implements ActionCommand {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {


        SubjectServiceSql subjectService = new SubjectServiceSql();
        List<Subject> subjects = subjectService.getAll();
        request.setAttribute("subjects",subjects);
        return new Page(PathPageManager.getProperty("page.period-mark")).setDispatchType(Page.DispatchType.FORWARD);
    }
}
