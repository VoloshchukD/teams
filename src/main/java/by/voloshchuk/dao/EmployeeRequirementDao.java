package by.voloshchuk.dao;

import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.exception.DaoException;

import java.util.List;

public interface EmployeeRequirementDao {

    boolean addEmployeeRequirement(EmployeeRequirement requirement) throws DaoException;

    List<EmployeeRequirement> findAllByTechnicalTaskId(Long technicalTaskId) throws DaoException;

    EmployeeRequirement updateEmployeeRequirement(EmployeeRequirement requirement) throws DaoException;

    boolean removeUserDetailById(Long id) throws DaoException;

}
