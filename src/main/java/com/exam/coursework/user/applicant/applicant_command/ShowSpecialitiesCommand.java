package com.exam.coursework.user.applicant.applicant_command;

import com.exam.coursework.shared.command.ActionCommand;
import com.exam.coursework.shared.web.Page;
import com.exam.coursework.user.speciality.Speciality;
import com.exam.coursework.user.speciality.SpecialityService;
import com.exam.coursework.utils.PathPageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class ShowSpecialitiesCommand implements ActionCommand {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        SpecialityService service = new SpecialityService();
        List<Speciality> specialities = service.getAll();
        request.setAttribute("specialities",specialities);
        return new Page(PathPageManager.getProperty("page.specialities")).setDispatchType(Page.DispatchType.FORWARD);
    }
}
