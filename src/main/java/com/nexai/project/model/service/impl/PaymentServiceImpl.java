package com.nexai.project.model.service.impl;

import com.nexai.project.exception.DaoException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.dao.DaoHelper;
import com.nexai.project.model.dao.PaymentDao;
import com.nexai.project.model.entity.Payment;
import com.nexai.project.model.service.PaymentService;

public class PaymentServiceImpl implements PaymentService {

    private static final PaymentDao paymentDao = DaoHelper.getInstance().getPaymentDao();

    @Override
    public void add(Payment entity) throws ServiceException {
        try {
            paymentDao.add(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
