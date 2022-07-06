package com.nexai.project.model.dao;

import com.nexai.project.exception.DaoException;
import com.nexai.project.model.entity.Accident;


import java.util.List;

public interface AccidentDao  extends Dao<Accident>{
    List<Accident> getAllByOrderId(Integer orderId) throws DaoException;
}
