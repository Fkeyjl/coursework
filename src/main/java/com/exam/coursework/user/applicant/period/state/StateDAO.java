package com.exam.coursework.user.applicant.period.state;


import com.exam.coursework.exception.UnsuccessfulDAOException;
import com.exam.coursework.shared.dao.AbstractDAO;

/**
 * Basic interface used for implementing FactoryDAO for switching easily
 */
public interface StateDAO extends AbstractDAO<Integer,State> {
    State getByName(String name) throws UnsuccessfulDAOException;
    State getByPeriodId(int id) throws UnsuccessfulDAOException;
}
