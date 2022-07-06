package com.nexai.project.model.dao;

import com.nexai.project.exception.DaoException;
import com.nexai.project.model.entity.User;
import com.nexai.project.model.entity.UserPassportData;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDao extends Dao<User> {

    void registerUser(String email, String password, UserPassportData passport) throws DaoException;
    void registerUser(String email, String password, UserPassportData passport, int role) throws DaoException;

    Optional<User> findUserByEmailAndPassword(String email, String password) throws DaoException, SQLException;
}
