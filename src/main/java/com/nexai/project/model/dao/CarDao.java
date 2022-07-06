package com.nexai.project.model.dao;

import com.nexai.project.exception.DaoException;
import com.nexai.project.model.entity.Car;
import com.nexai.project.model.entity.type.CarClass;

import java.math.BigDecimal;
import java.util.List;

public interface CarDao extends Dao<Car> {
    List<Car> getCarsByName(String criteria) throws DaoException;
    List<Car> getCarsByYear(String year) throws DaoException;
    List<Car> getCarsByClass(CarClass carClass) throws DaoException;

    void update(Car entity) throws DaoException;

    List<Car> findCarsByPricePerDayBetween(BigDecimal pricePerDayAfter, BigDecimal pricePerDayBefore) throws DaoException;

    List<Car> findCarsByCarType(CarClass carClass) throws DaoException;
    List<Car> findCarsByBrand(String brand) throws DaoException;
}
