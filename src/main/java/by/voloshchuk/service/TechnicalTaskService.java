package by.voloshchuk.service;

import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.exception.ServiceException;

import java.util.List;

public interface TechnicalTaskService {

    List<TechnicalTask> findTechnicalTasksByUserId(Long useId) throws ServiceException;

}
