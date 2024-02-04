package com.exam.coursework.user.applicant.applicant_command;

import com.exam.coursework.shared.command.ActionCommand;
import com.exam.coursework.shared.web.Page;
import com.exam.coursework.user.applicant.Applicant;
import com.exam.coursework.user.applicant.ApplicantService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ChoiceSpecialityCommandPOST implements ActionCommand {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        int id = (Integer) request.getSession().getAttribute("applicantId");
        ApplicantService service =new ApplicantService();
        Applicant applicant= service.getById(id);
        System.out.println(request.getParameter("idSpeciality"));
        int idSpeciality = Integer.parseInt(request.getParameter("idSpeciality"));
        applicant.setSpeciality(idSpeciality);
        applicant.setRating(service.calculateRatingForSubjectSpeciality(id,idSpeciality));
        service.update(applicant);
         return new Page( "/applicant/choose-speciality").setDispatchType(Page.DispatchType.REDIRECT);
    }
}
