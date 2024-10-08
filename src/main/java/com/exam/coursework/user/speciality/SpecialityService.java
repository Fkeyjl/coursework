package com.exam.coursework.user.speciality;

import com.exam.coursework.exception.UnsuccessfulDAOException;
import com.exam.coursework.shared.dao.FactoryDAO;
import com.exam.coursework.shared.dao.FactoryDAOSql;
import com.exam.coursework.shared.service.AbstractServiceSql;
import com.exam.coursework.shared.service.Service;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SpecialityService extends AbstractServiceSql<Integer,Speciality> implements Service<Integer,Speciality> {
    FactoryDAO factoryDAO;
    private  final Logger logger = Logger.getLogger(SpecialityService.class);
    public SpecialityService(){
        super(SpecialityDAOSql.class);
        factoryDAO= new FactoryDAOSql();
    }

    public Speciality getByApplicantId(int id) {
        Speciality speciality = null;
        try(Connection connection = factoryDAO.createConnection()) {
            SpecialityDAO specialityDAO = factoryDAO.createSpecialityDAO(connection);
            speciality = specialityDAO.getByApplicantId(id);
        } catch (SQLException | UnsuccessfulDAOException e) {
            logger.error(e.getMessage());
        }
        return speciality;
    }

    public List<Speciality> getAvailableSpecialitiesByApplicantId(int id) {
        List<Speciality> specialities = null;
        try(Connection connection = factoryDAO.createConnection()) {
            SpecialityDAO specialityDAO = factoryDAO.createSpecialityDAO(connection);
            specialities = specialityDAO.getAvailableByApplicantId(id);
        } catch (SQLException | UnsuccessfulDAOException e) {
            logger.error(e.getMessage());
        }
        return specialities;
    }
}
