package com.nexai.project.model.service;

import com.nexai.project.model.service.impl.*;

public final class ServiceProvider {

    private static final ServiceProvider instance = new ServiceProvider();

    private ServiceProvider() {
    }


    private final UserService userService = new UserServiceImpl();
    private final CarService carService = new CarServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final PaymentService paymentService = new PaymentServiceImpl();
    private final CarCommentService commentService = new CarCommentServiceImpl();
    private final NewsService newsService = new NewsServiceImpl();

    public static ServiceProvider getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public CarService getCarService() {
        return carService;
    }

    public OrderService getOrderService() {
        return orderService;
    }
    public NewsService getNewsService() {
        return newsService;
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }

    public CarCommentService getCommentService() {
        return commentService;
    }
}
