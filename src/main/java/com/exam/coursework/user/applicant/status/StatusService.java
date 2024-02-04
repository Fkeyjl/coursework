package com.exam.coursework.user.applicant.status;

import com.exam.coursework.exception.UnsuccessfulDAOException;
import com.exam.coursework.shared.dao.FactoryDAO;
import com.exam.coursework.shared.dao.FactoryDAOSql;
import com.exam.coursework.shared.service.AbstractServiceSql;
import com.exam.coursework.shared.service.Service;
import com.exam.coursework.user.applicant.ApplicantDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class StatusService extends AbstractServiceSql<Integer, Status> implements Service<Integer,Status> {
    private final Logger logger = Logger.getLogger(StatusService.class);
    FactoryDAO factoryDAO;

    public StatusService() {
        super(StatusDAOSql.class);
        factoryDAO = new FactoryDAOSql();
    }

    public Status getByApplicantId(int applicantId) {
        Status status = null;
        try (Connection connection = factoryDAO.createConnection()) {
            StatusDAO statusDAO = factoryDAO.createStatusDAO(connection);
            ApplicantDAO applicantDAO = factoryDAO.createApplicantDAO(connection);
            int id = applicantDAO.getById(applicantId).getStatus();
            status = statusDAO.getById(id);
        } catch (SQLException | UnsuccessfulDAOException e) {
            logger.error(e.getMessage());
        }
        return status;
    }
    public Status getByName(String name) {
        Status status = null;
        try (Connection connection = factoryDAO.createConnection()) {
            StatusDAO statusDAO = factoryDAO.createStatusDAO(connection);
            ApplicantDAO applicantDAO = factoryDAO.createApplicantDAO(connection);
            status = statusDAO.getByName(name);
        } catch (SQLException | UnsuccessfulDAOException e) {
            logger.error(e.getMessage());
        }
        return status;
    }


}
