package com.nexai.project.model.service;

import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.entity.Accident;

import java.util.List;

public interface AccidentService {
    List<Accident> getAll() throws ServiceException;
}
