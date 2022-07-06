package com.nexai.project.validation;


import com.nexai.project.model.entity.Order;
import com.nexai.project.model.entity.type.OrderStatus;
import com.nexai.project.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;


public class OrderValidator extends Validator {

    private static final Logger logger = LogManager.getLogger(OrderValidator.class);

    private static final DateUtil DATE_UTILS = new DateUtil();

    public boolean isCarAvailableForDates(List<Order> orders, int carId, LocalDate startDate, LocalDate endDate) {
        boolean isAvailable =  orders.stream()
                .filter(order -> order.getCar().getId() == carId
                        && (order.getStatus() == OrderStatus.APPROVED
                        || order.getStatus() == OrderStatus.PAID
                        || order.getStatus() == OrderStatus.RECEIVED))
                .noneMatch(order -> DATE_UTILS.isBetweenDates(startDate, order.getStartDate(), order.getEndDate())
                            && DATE_UTILS.isBetweenDates(endDate, order.getStartDate(), order.getEndDate()));
        if (!isAvailable) {
            message = DATE_UTILS.formatDate(startDate) + " : " + DATE_UTILS.formatDate(endDate);
        }
        return isAvailable;
    }

    public boolean isAlreadyMade(List<Order>orders, Order orderToCheck) {
        boolean isMade = orders.stream()
                .filter(order -> order.getUser().getId() == orderToCheck.getUser().getId()
                        && order.getCar().getId() == orderToCheck.getCar().getId() && order.getStatus() == OrderStatus.NEW)
                .anyMatch(order -> order.getStartDate().equals(orderToCheck.getStartDate())
                        && order.getEndDate().equals(orderToCheck.getEndDate()));
        if (isMade) {
            message = "Order is already made";
        }
        return isMade;
    }

    public boolean areDatesValid(LocalDate startDate, LocalDate endDate) {
        boolean isValid = true;
        if (startDate.isBefore(LocalDate.now())) {
            message = "Start date is earlier than today date";
            isValid = false;
        } else if (startDate.isAfter(endDate)) {
            message = "Start date is earlier than end date";
            isValid = false;
        }
        return isValid;
    }

}
