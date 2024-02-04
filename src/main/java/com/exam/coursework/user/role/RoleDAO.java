package com.exam.coursework.user.role;

import com.exam.coursework.exception.UnsuccessfulDAOException;
import com.exam.coursework.shared.dao.AbstractDAO;

/**
 * Basic interface used for implementing FactoryDAO for switching easily
 */
public interface RoleDAO  extends AbstractDAO<Integer, Role> {
     int getByName(String name) throws UnsuccessfulDAOException;
}
