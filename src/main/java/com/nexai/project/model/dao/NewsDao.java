package com.nexai.project.model.dao;

import com.nexai.project.exception.DaoException;
import com.nexai.project.model.entity.News;

public interface NewsDao extends Dao<News> {
    void update(News entity) throws DaoException;
}
