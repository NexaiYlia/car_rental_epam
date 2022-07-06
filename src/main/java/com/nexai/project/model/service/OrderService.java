package com.nexai.project.model.service;

import com.nexai.project.exception.InvalidDataException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.entity.Order;
import com.nexai.project.model.entity.type.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAll() throws ServiceException;
    List<Order> getAllByUserId(int userId) throws ServiceException;
    void add(Order entity) throws ServiceException, InvalidDataException;

    void changeStatus(int orderId, OrderStatus status) throws ServiceException;

    void addRejectionComment(int orderId, String rejectionComment) throws ServiceException;
    void addReturnComment(int orderId, String returnComment) throws ServiceException;

    Optional<Order> getById(int id) throws ServiceException;
}
