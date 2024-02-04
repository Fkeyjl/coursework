package com.exam.coursework.shared.service;

import com.exam.coursework.exception.UnsuccessfulDAOException;

import java.util.List;

public interface Service<K, E> {
    List<E> getAll();

    boolean update(E entity);

    boolean delete(K key);

    boolean create(E entity) throws UnsuccessfulDAOException;

    E getById(K key);
}
