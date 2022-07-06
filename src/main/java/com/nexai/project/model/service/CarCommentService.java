package com.nexai.project.model.service;


import com.nexai.project.exception.InvalidDataException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.entity.CarComment;

import java.util.List;

public interface CarCommentService {
    List<CarComment> getAll() throws ServiceException;
    List<CarComment> getAllByCarId(int carId) throws ServiceException;

    void add(CarComment entity) throws ServiceException, InvalidDataException;

    void deleteById(int id) throws ServiceException;

    int getDataAmount(int carId) throws ServiceException;
    List<CarComment> getCommentsForPage(int carId, int recordsPerPage, int currentPage) throws ServiceException;
}
