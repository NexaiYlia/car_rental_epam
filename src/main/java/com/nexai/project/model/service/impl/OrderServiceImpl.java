package com.nexai.project.model.service.impl;


import com.nexai.project.exception.DaoException;
import com.nexai.project.exception.InvalidDataException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.dao.DaoHelper;
import com.nexai.project.model.dao.OrderDao;
import com.nexai.project.model.entity.Order;
import com.nexai.project.model.entity.type.OrderStatus;
import com.nexai.project.model.service.OrderService;
import com.nexai.project.validation.OrderValidator;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private static final OrderDao ORDER_DAO = DaoHelper.getInstance().getOrderDao();

    @Override
    public Optional<Order> getById(int id) throws ServiceException {
        try {
            return ORDER_DAO.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getAll() throws ServiceException {
        try {
            return ORDER_DAO.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getAllByUserId(int userId) throws ServiceException {
        try {
            return ORDER_DAO.getAllByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void add(Order entity) throws ServiceException, InvalidDataException {
        OrderValidator validator = new OrderValidator();

        int carId = entity.getCar().getId();
        LocalDate startDate = entity.getStartDate();
        LocalDate endDate = entity.getEndDate();

        List<Order> orders = getAll();

        if (!validator.isCarAvailableForDates(orders, carId, startDate, endDate)
                || !validator.areDatesValid(startDate, endDate)
                || validator.isAlreadyMade(orders, entity)) {
            throw new InvalidDataException(validator.getMessage());
        }

        try {
            ORDER_DAO.add(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeStatus(int orderId, OrderStatus status) throws ServiceException {
        try {
            ORDER_DAO.changeStatus(orderId, status);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addRejectionComment(int orderId, String rejectionComment) throws ServiceException {
        try {
            ORDER_DAO.addRejectionComment(orderId, rejectionComment);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addReturnComment(int orderId, String returnComment) throws ServiceException {
        try {
            ORDER_DAO.addReturnComment(orderId, returnComment);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
