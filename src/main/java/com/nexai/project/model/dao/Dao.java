package com.nexai.project.model.dao;

import com.nexai.project.exception.DaoException;
import com.nexai.project.model.entity.Identifiable;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public interface Dao<T extends Identifiable> {
    Optional<T> getById(int id) throws DaoException;

    List<T> getAll() throws DaoException;

    void add(T entity) throws DaoException;

    void deleteById(int id) throws DaoException;

    List<T> executeForManyResults(PreparedStatement statement) throws SQLException, DaoException;

    Optional<T> executeForSingleResult(PreparedStatement statement) throws SQLException, DaoException;
}
