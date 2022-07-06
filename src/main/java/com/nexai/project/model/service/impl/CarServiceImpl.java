package com.nexai.project.model.service.impl;

import com.nexai.project.exception.DaoException;
import com.nexai.project.exception.InvalidDataException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.dao.CarDao;
import com.nexai.project.model.dao.DaoHelper;
import com.nexai.project.model.entity.Car;
import com.nexai.project.model.entity.type.CarClass;
import com.nexai.project.model.service.CarService;
import com.nexai.project.validation.CarValidator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class CarServiceImpl implements CarService {

    private static final CarValidator VALIDATOR = new CarValidator();
    private static final CarDao CAR_DAO = DaoHelper.getInstance().getCarDao();

    @Override
    public Optional<Car> getById(int id) throws ServiceException {
        try {
            return CAR_DAO.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Car> getAll() throws ServiceException {
        try {
            return CAR_DAO.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Car> getCarsByName(String criteria) throws ServiceException {
        try {
            return CAR_DAO.getCarsByName(criteria);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Car> getCarsByYear(String year) throws ServiceException {
        try {
            return CAR_DAO.getCarsByYear(year);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Car> getCarsByClass(CarClass carClass) throws ServiceException {
        try {
            return CAR_DAO.getCarsByClass(carClass);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Car entity) throws ServiceException, InvalidDataException {

        if (!VALIDATOR.isPriceValid(entity.getPricePerDay())
                || !VALIDATOR.isYearValid(entity.getManufacturedYear())) {
            throw new InvalidDataException(VALIDATOR.getMessage());
        }

        try {
            CAR_DAO.update(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void add(Car car) throws ServiceException, InvalidDataException {


        BigDecimal price = car.getPricePerDay();

        String year = car.getManufacturedYear();

        if (!VALIDATOR.isPriceValid(price)
                || !VALIDATOR.isYearValid(year)) {
            throw new InvalidDataException(VALIDATOR.getMessage());
        }

        try {
            CAR_DAO.add(car);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteById(int id) throws ServiceException {

        try {
            CAR_DAO.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
