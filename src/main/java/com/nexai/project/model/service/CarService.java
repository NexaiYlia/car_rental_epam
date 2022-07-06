package com.nexai.project.model.service;

import com.nexai.project.exception.DaoException;
import com.nexai.project.exception.InvalidDataException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.entity.Car;
import com.nexai.project.model.entity.type.CarClass;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CarService {
    Optional<Car> getById(int id) throws ServiceException;

    List<Car> getAll() throws ServiceException;

    List<Car> getCarsByName(String criteria) throws ServiceException;

    List<Car> getCarsByYear(String year) throws ServiceException;

    List<Car> getCarsByClass(CarClass carClass) throws ServiceException;

    void update(Car entity) throws ServiceException, InvalidDataException;

    void add(Car car) throws ServiceException, InvalidDataException;

    void deleteById(int id) throws ServiceException;

    List<Car> findCarsByPricePerDayBetween(BigDecimal pricePerDayAfter, BigDecimal pricePerDayBefore) throws DaoException;

    List<Car> findCarsByCarType(CarClass carType) throws DaoException;

    List<Car> findCarsByBrand(String brand) throws DaoException;
}
