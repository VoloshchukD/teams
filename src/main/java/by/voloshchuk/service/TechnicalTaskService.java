package by.voloshchuk.service;

import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;

import java.util.List;

public interface TechnicalTaskService {

    boolean addTechnicalTask(TechnicalTask technicalTask) throws ServiceException;

    TechnicalTask findTechnicalTaskById(Long technicalTaskId) throws ServiceException;

    List<TechnicalTask> findTechnicalTasksByUserId(Long useId) throws ServiceException;

    List<TechnicalTask> findTechnicalTasksByStatus(String status) throws ServiceException;

}
