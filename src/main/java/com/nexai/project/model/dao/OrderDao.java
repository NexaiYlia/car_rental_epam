package com.nexai.project.model.dao;

import com.nexai.project.exception.DaoException;
import com.nexai.project.model.entity.Order;
import com.nexai.project.model.entity.type.OrderStatus;

import java.util.List;

public interface OrderDao extends Dao<Order>{
    List<Order> getAllByUserId(int userId) throws DaoException;

    void changeStatus(int orderId, OrderStatus status) throws DaoException;

    void addRejectionComment(int orderId, String comment) throws DaoException;
    void addReturnComment(int orderId, String comment) throws DaoException;
}
