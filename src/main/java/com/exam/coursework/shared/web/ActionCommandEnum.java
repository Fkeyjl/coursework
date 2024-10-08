package com.exam.coursework.shared.web;


import com.exam.coursework.security.command.*;
import com.exam.coursework.shared.annotations.Action;
import com.exam.coursework.shared.command.ActionCommand;
import com.exam.coursework.user.admin_command.*;
import com.exam.coursework.user.applicant.applicant_command.*;

public enum ActionCommandEnum {
    @Action(url = "/admin/create-new-period", method = "POST")
    CREATE_NEW_PERIOD_POST {
        {
            this.command = new CreateNewPeriodCommand();
        }
    },

    @Action(url = "/admin/create-new-period", method = "GET")
    CREATE_NEW_PERIOD_GET {
        {
            this.command = new CreateNewPeriodCommandGet();
        }
    },

    @Action(url = "/login/sign-in", method = "POST")
    SIGN_IN {
        {
            this.command = new SingInCommandPOST();
        }
    },

    @Action(url = "/login/sign-in", method = "GET")
    SIGN_IN_GET {
        {
            this.command = new SignInCommand();
        }
    },

    @Action(url = "/login/sign-up", method = "GET")
    SIGN_UP_GET {
        {
            this.command = new SignUpCommand();
        }
    },
    @Action(url = "/login/sign-up", method = "POST")
    SIGN_UP_POST {
        {
            this.command = new SingUpCommandPOST();
        }
    },
    @Action(url = "/login/logout", method = "GET")
    LOG_OUT {
        {
            this.command = new LogOutCommand();
        }
    },
    @Action(url = "/applicant", method = "GET")
    APPLICANT_HOME {
        {
            this.command = new ApplicantHomeCommand();
        }
    },
    @Action(url = "/admin", method = "GET")
    ADMIN_HOME {
        {
            this.command = new AdminHomeCommand();
        }
    },
    @Action(url = "/period", method = "GET")
    PERIOD {
        {
            this.command = new PeriodCommand();
        }
    },
    @Action(url = "/period", method = "POST")
    PERIOD_POST {
        {
            this.command = new PeriodCommandPOST();
        }
    },
    @Action(url = "/applicant/choose-subjects", method = "GET")
    CHOICE {
        {
            this.command = new ChoiceSubjectCommand();
        }
    },
    @Action(url = "/applicant/choose-subjects", method = "POST")
    CHOICE_POST {
        {
            this.command = new ChoiceSubjectCommandPOST();
        }
    },
    @Action(url = "/admin/periods", method = "GET")
    PERIODS {
        {
            this.command = new ShowPeriodsCommand();
        }
    },
    @Action(url = "/admin/period-info", method = "GET")
    PERIOD_INFO {
        {
            this.command = new PeriodInfoCommand();
        }
    },
    @Action(url = "/admin/period-mark", method = "GET")
    PERIOD_MARK {
        {
            this.command = new SelectSubjectMark();
        }
    },
    @Action(url = "/admin/period-edit", method = "GET")
    PERIOD_EDIT {
        {
            this.command = new PeriodEditCommand();
        }
    },
    @Action(url = "/admin/period-edit", method = "POST")
    PERIOD_EDIT_POST {
        {
            this.command = new PeriodEditCommandPOST();
        }
    },
    @Action(url = "/applicant/info", method = "GET")
    APPLICATION_INFO {
        {
            this.command = new ApplicantInfoCommand();
        }
    },
    @Action(url = "/admin/period-subject-marks", method = "GET")
    PERIOD_SUBJECT_MARKS {
        {
            this.command = new PeriodSubjectMarks();
        }
    },
    @Action(url = "/admin/period-subject-marks", method = "POST")
    PERIOD_SUBJECT_MARKS_POST {
        {
            this.command = new PeriodSubjectMarksPOST();
        }
    },
    @Action(url = "/applicant/choose-speciality", method = "GET")
    CHOICE_SPECIALITY{
        {
            this.command = new ChoiceSpecialityCommand();
        }
    },
    @Action(url = "/applicant/choose-speciality", method = "POST")
    CHOICE_SPECIALITY_POST{
        {
            this.command = new ChoiceSpecialityCommandPOST();
        }
    },

    @Action(url = "/admin/specialities", method = "GET")
    SHOW_SPECIALITIES{
        {
            this.command = new ShowSpecialitiesCommand();
        }
    },
    @Action(url = "/admin/statistic", method = "GET")
    SHOW_STATISTICS{
        {
            this.command = new ShowStatisticBySpeciality();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
