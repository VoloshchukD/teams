package by.voloshchuk.service.impl;

import by.voloshchuk.dao.DaoProvider;
import by.voloshchuk.dao.EmployeeRequirementDao;
import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.EmployeeRequirementService;

import java.util.List;

public class EmployeeRequirementServiceImpl implements EmployeeRequirementService {

    private static DaoProvider daoProvider = DaoProvider.getInstance();

    @Override
    public List<EmployeeRequirement> findAllByTechnicalTaskId(Long technicalTaskId) throws ServiceException {
        List<EmployeeRequirement> employeeRequirements = null;
        EmployeeRequirementDao employeeRequirementDao = daoProvider.getEmployeeRequirementDao();
        try {
            employeeRequirements = employeeRequirementDao.findAllByTechnicalTaskId(technicalTaskId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return employeeRequirements;
    }

}
