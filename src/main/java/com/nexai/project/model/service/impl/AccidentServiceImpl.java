package com.nexai.project.model.service.impl;

import com.nexai.project.exception.DaoException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.dao.AccidentDao;
import com.nexai.project.model.dao.CarDao;
import com.nexai.project.model.dao.DaoHelper;
import com.nexai.project.model.entity.Accident;
import com.nexai.project.model.service.AccidentService;

import java.util.List;

public class AccidentServiceImpl implements AccidentService {

    private static final AccidentDao ACCIDENT_DAO = DaoHelper.getInstance().getAccidentDao();

    @Override
    public List<Accident> getAll() throws ServiceException {
        try {
            return ACCIDENT_DAO.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
