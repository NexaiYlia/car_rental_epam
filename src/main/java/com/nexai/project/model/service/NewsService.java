package com.nexai.project.model.service;

import com.nexai.project.exception.InvalidDataException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.entity.News;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    Optional<News> findNewsById(int id) throws ServiceException;

    List<News> getAll() throws ServiceException;

    void update(News entity) throws ServiceException, InvalidDataException;

    void add(News news) throws ServiceException, InvalidDataException;

    void deleteById(int id) throws ServiceException;
}
