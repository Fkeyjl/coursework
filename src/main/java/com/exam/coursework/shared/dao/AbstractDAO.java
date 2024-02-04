package com.exam.coursework.shared.dao;



import com.exam.coursework.exception.UnsuccessfulDAOException;

import java.util.List;

/**
 * Interface for DAO to Database
 * @param <K> key of the database table
 * @param <E> entity of model
 */
public interface AbstractDAO<K, E> {

    List<E> getAll() throws UnsuccessfulDAOException;

    boolean update(E entity) throws UnsuccessfulDAOException;

    boolean delete(K k) throws UnsuccessfulDAOException;

    boolean create(E entity) throws UnsuccessfulDAOException;

    E getById(K k) throws UnsuccessfulDAOException;


}
