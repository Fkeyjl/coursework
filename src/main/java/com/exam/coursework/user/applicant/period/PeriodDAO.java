package com.exam.coursework.user.applicant.period;

import com.exam.coursework.exception.UnsuccessfulDAOException;
import com.exam.coursework.shared.dao.AbstractDAO;

import java.util.List;

/**
 * Basic interface used for implementing FactoryDAO for switching easily
 */
public interface PeriodDAO extends AbstractDAO<Integer,Period> {
    List<Period> getAvailablePeriodWithStateForUserIdOrStateId(int userId, int stateIdd) throws UnsuccessfulDAOException;
}
