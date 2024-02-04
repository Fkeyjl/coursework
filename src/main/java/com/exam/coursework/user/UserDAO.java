package com.exam.coursework.user;


import com.exam.coursework.exception.UnsuccessfulDAOException;
import com.exam.coursework.shared.dao.AbstractDAO;

public interface UserDAO <K> extends AbstractDAO<K ,User> {
     User findByLoginAndPassword(String login, String password) throws UnsuccessfulDAOException;
}
