package com.nexai.project.model.service;

import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.entity.Payment;

public interface PaymentService {
    void add(Payment entity) throws ServiceException;
}
