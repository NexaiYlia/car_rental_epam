package com.nexai.project.model.dao;

import com.nexai.project.exception.DaoException;
import com.nexai.project.model.entity.Car;
import com.nexai.project.model.entity.type.CarClass;

import java.util.List;

public interface CarDao extends Dao<Car> {
    List<Car> getCarsByName(String criteria) throws DaoException;
    List<Car> getCarsByYear(String year) throws DaoException;
    List<Car> getCarsByClass(CarClass carClass) throws DaoException;

    void update(Car entity) throws DaoException;
}
