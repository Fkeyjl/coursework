package com.exam.coursework.user.applicant.applicant_command;

import com.exam.coursework.exception.UnsuccessfulDAOException;
import com.exam.coursework.shared.command.ActionCommand;
import com.exam.coursework.shared.web.Page;
import com.exam.coursework.user.subject.SubjectServiceSql;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;


public class ChoiceSubjectCommandPOST implements ActionCommand {
    private static final Logger logger = Logger.getLogger(SubjectServiceSql.class);

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer applicantId = (Integer) session.getAttribute("applicantId");
        String type = request.getParameter("type");
        if (type.equals("delete")) {
            int subjectId = Integer.parseInt( request.getParameter("idSelectedSubject"));
            deleteSubjectBySubjectIdAndApplicantId(subjectId, applicantId);
        } else {
            int id = Integer.parseInt(request.getParameter("idSubject"));
            System.out.println(id);

            try {
                new SubjectServiceSql().addSubjectApplicant(id, applicantId);
            } catch (UnsuccessfulDAOException e) {
                logger.error(e.getMessage());
            }
        }
        return new Page("/applicant/choose-subjects").setDispatchType(Page.DispatchType.REDIRECT);

    }

    public boolean deleteSubjectBySubjectIdAndApplicantId(int subjectId, int applicantId) {
        SubjectServiceSql subjectService = new SubjectServiceSql();
        return subjectService.deleteSubjectBySubjectIdAndApplicantId(subjectId,applicantId);
    }

}
