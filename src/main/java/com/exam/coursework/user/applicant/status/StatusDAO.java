package com.exam.coursework.user.applicant.status;

import com.exam.coursework.exception.UnsuccessfulDAOException;
import com.exam.coursework.shared.dao.AbstractDAO;

/**
 * Basic interface used for implementing FactoryDAO for switching easily
 */
public interface StatusDAO extends AbstractDAO<Integer, Status> {
    Status getByName(String name) throws UnsuccessfulDAOException;
}
