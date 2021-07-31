package by.voloshchuk.service;

import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;

import java.util.List;

public interface EmployeeRequirementService {

    List<EmployeeRequirement> findAllByTechnicalTaskId(Long technicalTaskId) throws ServiceException;

}
