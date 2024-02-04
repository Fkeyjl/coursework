package com.exam.coursework.user.admin_command;

import com.exam.coursework.shared.command.ActionCommand;
import com.exam.coursework.shared.web.Page;
import com.exam.coursework.user.applicant.Applicant;
import com.exam.coursework.user.applicant.ApplicantService;
import com.exam.coursework.utils.PathPageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.util.List;

public class ShowStatisticBySpeciality implements ActionCommand {


    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        final int NUMBER_OF_APPLICANT_ON_PAGE = 6;
        ApplicantService serviceSql = new ApplicantService();
        int specialityId = Integer.parseInt(request.getParameter("speciality"));
        int page = Integer.parseInt(request.getParameter("page"));
        int periodId = Integer.parseInt(request.getParameter("id-period"));
        int numberOfApplicant = serviceSql.getNumberOfApplicantBySpecialityIdAndPeriodId(specialityId, periodId);
        int lastPage = getNumberOfPages(numberOfApplicant, NUMBER_OF_APPLICANT_ON_PAGE);
        if (page > lastPage||page<1) {
            return new Page("/admin/statistic?id-period="+periodId+"&speciality="+ specialityId+"&page=1").setDispatchType(Page.DispatchType.REDIRECT);
        }

        List<Applicant> applicants = serviceSql.getBySpecialityIdAndPeriodId(specialityId, periodId,NUMBER_OF_APPLICANT_ON_PAGE*(page-1),NUMBER_OF_APPLICANT_ON_PAGE);
        request.setAttribute("applicants", applicants);
        request.setAttribute("currentPage", page);

        request.setAttribute("lastPage", getNumberOfPages(numberOfApplicant, NUMBER_OF_APPLICANT_ON_PAGE));
        System.out.println(applicants);
        return new Page(PathPageManager.getProperty("page.statistics")).setDispatchType(Page.DispatchType.FORWARD);
    }

    int getNumberOfPages(int totalNumberOrLine, int numberOnPage) {
        if (totalNumberOrLine == 0)
            return 1;
        if (totalNumberOrLine % numberOnPage == 0)
            return totalNumberOrLine / numberOnPage;
        else
            return totalNumberOrLine / numberOnPage + 1;
    }

}
