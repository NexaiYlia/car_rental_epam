package com.nexai.project.util;

import com.nexai.project.model.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PriceCalculator {

    private static final DateUtil DATE_UTILS = new DateUtil();

    public BigDecimal calculatePrice(Order order) {
        BigDecimal carPricePerDay = order.getCar().getPricePerDay();

        LocalDate startDate = order.getStartDate();
        LocalDate endDate = order.getEndDate();

        BigDecimal daysInTotal = new BigDecimal(DATE_UTILS.calculateDaysBetweenDates(startDate, endDate) + 1);
        return daysInTotal.multiply(carPricePerDay);
    }
}