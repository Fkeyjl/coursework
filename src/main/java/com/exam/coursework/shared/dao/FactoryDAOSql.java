package com.exam.coursework.shared.dao;


import com.exam.coursework.shared.service.SQLConnection;
import com.exam.coursework.user.UserDAO;
import com.exam.coursework.user.UserDAOSql;
import com.exam.coursework.user.applicant.ApplicantDAO;
import com.exam.coursework.user.applicant.ApplicantDAOSql;
import com.exam.coursework.user.applicant.period.PeriodDAO;
import com.exam.coursework.user.applicant.period.PeriodDAOSql;
import com.exam.coursework.user.applicant.period.state.StateDAO;
import com.exam.coursework.user.applicant.period.state.StateDAOSql;
import com.exam.coursework.user.applicant.status.StatusDAO;
import com.exam.coursework.user.applicant.status.StatusDAOSql;
import com.exam.coursework.user.role.RoleDAO;
import com.exam.coursework.user.role.RoleDAOSql;
import com.exam.coursework.user.speciality.SpecialityDAO;
import com.exam.coursework.user.speciality.SpecialityDAOSql;
import com.exam.coursework.user.subject.SubjectDAO;
import com.exam.coursework.user.subject.SubjectDAOSql;

import java.sql.Connection;

public class FactoryDAOSql implements FactoryDAO {

    @Override
    public UserDAO createUserDAO(Connection connection) {
        return new UserDAOSql(connection);
    }

    @Override
    public RoleDAO createUserRoleDAO(Connection connection) {
        return new RoleDAOSql(connection);
    }

    @Override
    public SubjectDAO createSubjectDAO(Connection connection) {
        return new SubjectDAOSql(connection);
    }

    @Override
    public ApplicantDAO createApplicantDAO(Connection connection) {
        return new ApplicantDAOSql(connection);
    }

    @Override
    public PeriodDAO createPeriodDAO(Connection connection) {
        return new PeriodDAOSql(connection);
    }

    @Override
    public StateDAO createStateDAO(Connection connection) {
        return new StateDAOSql(connection);
    }

    @Override
    public StatusDAO createStatusDAO(Connection connection) {
        return new StatusDAOSql(connection);
    }

    @Override
    public Connection createConnection() {
        return new SQLConnection().getConnection();
    }

    @Override
    public SpecialityDAO createSpecialityDAO(Connection connection) {
        return new SpecialityDAOSql(connection);
    }
}
