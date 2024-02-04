package com.exam.coursework.user.applicant.applicant_command;

import com.exam.coursework.shared.command.ActionCommand;
import com.exam.coursework.shared.web.Page;
import com.exam.coursework.user.applicant.ApplicantService;
import com.exam.coursework.user.applicant.period.state.StateName;
import com.exam.coursework.user.applicant.period.state.StateServiceSql;
import com.exam.coursework.user.speciality.Speciality;
import com.exam.coursework.user.speciality.SpecialityService;
import com.exam.coursework.utils.PathPageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class ChoiceSpecialityCommand implements ActionCommand {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        int id = (Integer) request.getSession().getAttribute("applicantId");

        
        ApplicantService applicantService = new ApplicantService();
        StateServiceSql stateService = new StateServiceSql();


        StateName stateName= stateService.getByPeriodId(applicantService.getById(id).getPeriod());
        if(!stateName.equals(StateName.CHOICE_SPECIALITY)){
            request.setAttribute("access", false);
        } else
            request.setAttribute("access", true);



        SpecialityService specialityService = new SpecialityService();
        List<Speciality> specialities = specialityService.getAvailableSpecialitiesByApplicantId(id);
        request.setAttribute("specialities", specialities);
        System.out.println(specialities);
        return new Page(PathPageManager.getProperty("page.choice-speciality")).setDispatchType(Page.DispatchType.FORWARD);
    }
}
