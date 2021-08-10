package by.voloshchuk.service;

import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.entity.dto.TechnicalTaskDto;
import by.voloshchuk.exception.ServiceException;

import java.util.List;

public interface TechnicalTaskService {

    boolean addTechnicalTask(TechnicalTaskDto technicalTaskDto) throws ServiceException;

    TechnicalTask findTechnicalTaskById(Long technicalTaskId) throws ServiceException;

    List<TechnicalTask> findTechnicalTasksByUserId(Long useId) throws ServiceException;

    List<TechnicalTask> findTechnicalTasksByStatus(String status) throws ServiceException;

    TechnicalTask updateTechnicalTask(TechnicalTask technicalTask) throws ServiceException;

    boolean removeTechnicalTask(Long id) throws ServiceException;

}
