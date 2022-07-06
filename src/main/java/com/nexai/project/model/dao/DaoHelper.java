package com.nexai.project.model.dao;

import com.nexai.project.model.dao.impl.*;

public final class DaoHelper {
    private final static DaoHelper instance = new DaoHelper();

    public static DaoHelper getInstance() {
        return instance;
    }

    private final UserDao userDao = new UserDaoImpl();
    private final AccidentDao accidentDao = new AccidentDaoImpl();
    private final CarDao carDao = new CarDaoImpl();
    private final OrderDao orderDao = new OrderDaoImpl();

    private final NewsDao newsDao = new NewsDaoImpl();
    private final UserPassportDataDao passportDao = new UserPassportDataDaoImpl();
    private final PaymentDao paymentDao = new PaymentDaoImpl();
    private final CarCommentDao carCommentDao = new CarCommentDaoImpl();


    public UserDao getUserDao() {
        return userDao;
    }

    public AccidentDao getAccidentDao() {
        return accidentDao;
    }

    public CarDao getCarDao() {
        return carDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public UserPassportDataDao getPassportDao() {
        return passportDao;
    }

    public PaymentDao getPaymentDao() {
        return paymentDao;
    }

    public CarCommentDao getCarCommentDao() {
        return carCommentDao;
    }

    public NewsDao getNewsDao() {
        return newsDao;
    }
}
