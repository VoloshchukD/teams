package by.voloshchuk.service.impl;

import by.voloshchuk.dao.DaoProvider;
import by.voloshchuk.dao.EmployeeRequirementDao;
import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.entity.dto.EmployeeRequirementDto;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.EmployeeRequirementService;
import by.voloshchuk.service.validator.Validator;
import by.voloshchuk.service.validator.ValidatorProvider;

import java.util.List;

public class EmployeeRequirementServiceImpl implements EmployeeRequirementService {

    private static DaoProvider daoProvider = DaoProvider.getInstance();

    @Override
    public boolean addEmployeeRequirement(EmployeeRequirementDto requirementDto) throws ServiceException {
        boolean result = false;
        EmployeeRequirementDao employeeRequirementDao = daoProvider.getEmployeeRequirementDao();
        Validator<EmployeeRequirementDto> requirementValidator
                = ValidatorProvider.getInstance().getEmployeeRequirementValidator();
        if (requirementValidator.validateCreateData(requirementDto)) {
            try {
                EmployeeRequirement employeeRequirement = buildEmployeeRequirement(requirementDto);
                Long technicalTaskId = Long.parseLong(requirementDto.getTechnicalTaskId());
                TechnicalTask technicalTask = new TechnicalTask();
                technicalTask.setId(technicalTaskId);
                employeeRequirement.setTechnicalTask(technicalTask);
                result = employeeRequirementDao.addEmployeeRequirement(employeeRequirement);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    private EmployeeRequirement buildEmployeeRequirement(EmployeeRequirementDto requirement) {
        EmployeeRequirement employeeRequirement = new EmployeeRequirement();
        employeeRequirement.setPrimarySkill(requirement.getPrimarySkill());
        employeeRequirement.setSalary(Integer.parseInt(requirement.getSalary()));
        employeeRequirement.setExperience(Integer.parseInt(requirement.getExperience()));
        employeeRequirement.setQualification(requirement.getQualification());
        employeeRequirement.setComment(requirement.getComment());
        return employeeRequirement;
    }

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

    @Override
    public List<EmployeeRequirement> findAllByProjectId(Long projectId) throws ServiceException {
        List<EmployeeRequirement> employeeRequirements = null;
        EmployeeRequirementDao employeeRequirementDao = daoProvider.getEmployeeRequirementDao();
        try {
            employeeRequirements = employeeRequirementDao.findAllByProjectId(projectId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return employeeRequirements;
    }

    @Override
    public EmployeeRequirement updateEmployeeRequirement(EmployeeRequirementDto requirementDto)
            throws ServiceException {
        EmployeeRequirement updatedRequirement = null;
        EmployeeRequirementDao requirementDao = daoProvider.getEmployeeRequirementDao();
        Validator<EmployeeRequirementDto> requirementValidator
                = ValidatorProvider.getInstance().getEmployeeRequirementValidator();
        if (requirementValidator.validateUpdateData(requirementDto)) {
            EmployeeRequirement requirement = buildEmployeeRequirement(requirementDto);
            requirement.setId(requirementDto.getRequirementId());
            try {
                updatedRequirement = requirementDao.updateEmployeeRequirement(requirement);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return updatedRequirement;
    }

    @Override
    public boolean removeEmployeeRequirement(Long id) throws ServiceException {
        boolean deleted = false;
        EmployeeRequirementDao requirementDao = daoProvider.getEmployeeRequirementDao();
        try {
            deleted = requirementDao.removeEmployeeRequirement(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return deleted;
    }

}
