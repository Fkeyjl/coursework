package com.exam.coursework.shared.dao;

import com.exam.coursework.user.UserDAO;
import com.exam.coursework.user.applicant.ApplicantDAO;
import com.exam.coursework.user.applicant.period.PeriodDAO;
import com.exam.coursework.user.applicant.period.state.StateDAO;
import com.exam.coursework.user.applicant.status.StatusDAO;
import com.exam.coursework.user.role.RoleDAO;
import com.exam.coursework.user.speciality.SpecialityDAO;
import com.exam.coursework.user.subject.SubjectDAO;

import java.sql.Connection;

public interface FactoryDAO {
    UserDAO createUserDAO(Connection connection);
    RoleDAO createUserRoleDAO(Connection connection);
    SubjectDAO createSubjectDAO(Connection connection);
    ApplicantDAO createApplicantDAO(Connection connection);
    PeriodDAO createPeriodDAO(Connection connection);
    StateDAO createStateDAO(Connection connection);
    StatusDAO createStatusDAO(Connection connection);
    SpecialityDAO createSpecialityDAO(Connection connection);
    Connection createConnection();
}
