package com.nexai.project.model.service;

import com.nexai.project.exception.InvalidDataException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.entity.User;
import com.nexai.project.model.entity.UserPassportData;

import java.util.Optional;

public interface UserService {
    Optional<User> getById(Integer id) throws ServiceException;
    Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException, InvalidDataException;

    void registerUser(String email, String password,  UserPassportData passport) throws ServiceException, InvalidDataException;
}
