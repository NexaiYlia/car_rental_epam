package com.nexai.project.model.dao;

import com.nexai.project.exception.DaoException;
import com.nexai.project.model.entity.CarComment;

import java.util.List;

public interface CarCommentDao extends Dao<CarComment> {
    List<CarComment> getAllByCarId(int carId) throws DaoException;

    int getDataAmount(int carId) throws DaoException;
    List<CarComment> getCommentsForPage(int carId, int limit, int offset) throws DaoException;
}
