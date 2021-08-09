package by.voloshchuk.service;

import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;

import java.util.List;

public interface EmployeeRequirementService {

    boolean addEmployeeRequirement(EmployeeRequirement requirement) throws ServiceException;

    List<EmployeeRequirement> findAllByTechnicalTaskId(Long technicalTaskId) throws ServiceException;

    List<EmployeeRequirement> findAllByProjectId(Long projectId) throws ServiceException;

    EmployeeRequirement updateEmployeeRequirement(EmployeeRequirement requirement) throws ServiceException;

    boolean removeEmployeeRequirement(Long id) throws ServiceException;

}
