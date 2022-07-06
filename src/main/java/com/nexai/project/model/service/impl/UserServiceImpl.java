package com.nexai.project.model.service.impl;


import com.nexai.project.exception.DaoException;
import com.nexai.project.exception.InvalidDataException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.dao.DaoHelper;
import com.nexai.project.model.dao.UserDao;
import com.nexai.project.model.entity.User;
import com.nexai.project.model.entity.UserPassportData;
import com.nexai.project.model.service.UserService;
import com.nexai.project.validation.UserValidator;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final UserDao USER_DAO = DaoHelper.getInstance().getUserDao();
    private static final UserValidator VALIDATOR = new UserValidator();

    @Override
    public Optional<User> getById(Integer id) throws ServiceException {
        try {
            return USER_DAO.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException, InvalidDataException {
        if (!VALIDATOR.isValidEmail(email) || !VALIDATOR.isValidPassword(password)) {
            throw new InvalidDataException(VALIDATOR.getMessage());
        }

        try {
            password = DigestUtils.md5Hex(password);
            return USER_DAO.findUserByEmailAndPassword(email, password);
        } catch (DaoException | SQLException e) {
            throw new ServiceException();
        }
    }

    @Override
    public void registerUser(String email, String password,  UserPassportData passport) throws ServiceException, InvalidDataException {
        if (!VALIDATOR.isValidEmail(email) || !VALIDATOR.isValidPassword(password)) {
            throw new InvalidDataException(VALIDATOR.getMessage());
        }

        try {
            password = DigestUtils.md5Hex(password);
            USER_DAO.registerUser(email, password, passport);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }
}
