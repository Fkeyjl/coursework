package com.exam.coursework.user.speciality;

import com.exam.coursework.exception.UnsuccessfulDAOException;
import com.exam.coursework.shared.dao.AbstractDAO;

import java.util.List;

/**
 * Basic interface used for implementing FactoryDAO for switching easily
 */
public interface SpecialityDAO extends AbstractDAO<Integer,Speciality> {
    List<Speciality> getAvailableByApplicantId(int id) throws UnsuccessfulDAOException;
    Speciality getByApplicantId(int id) throws UnsuccessfulDAOException;
}
