package com.nexai.project.model.dao;

import com.nexai.project.exception.DaoException;
import com.nexai.project.model.entity.UserPassportData;

import java.util.Optional;

public interface UserPassportDataDao {
    void add(UserPassportData entity) throws DaoException;

    Optional<UserPassportData> getByPassportNumber(String passportNumber) throws DaoException;
}
