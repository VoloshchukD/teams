package by.voloshchuk.dao.impl;

import by.voloshchuk.dao.ConstantDaoQuery;
import by.voloshchuk.dao.DaoExecutor;
import by.voloshchuk.dao.EmployeeRequirementDao;
import by.voloshchuk.dao.builder.Builder;
import by.voloshchuk.dao.builder.EmployeeRequirementBuilder;
import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.exception.DaoException;

import java.util.List;

public class EmployeeRequirementDaoImpl implements EmployeeRequirementDao {

    private final DaoExecutor<EmployeeRequirement> executor;

    public EmployeeRequirementDaoImpl() {
        Builder<EmployeeRequirement> builder = new EmployeeRequirementBuilder();
        executor = new DaoExecutor<>(builder);
    }

    @Override
    public boolean addEmployeeRequirement(EmployeeRequirement requirement) throws DaoException {
        Object[] parameters = {requirement.getExperience(), requirement.getSalary(),
                requirement.getQualification(), requirement.getPrimarySkill(),
                requirement.getComment(), requirement.getTechnicalTaskId()};
        boolean added = executor.executeUpdate(
                ConstantDaoQuery.ADD_EMPLOYEE_REQUIREMENT_QUERY, parameters);
        return added;
    }

    @Override
    public List<EmployeeRequirement> findAllByTechnicalTaskId(Long technicalTaskId)
            throws DaoException {
        Object[] parameters = {technicalTaskId};
        List<EmployeeRequirement> requirements = executor.executeQueryMultipleResult(
                ConstantDaoQuery.FIND_ALL_BY_TECHNICAL_TASK_ID_QUERY, parameters);
        return requirements;
    }

    @Override
    public List<EmployeeRequirement> findAllByProjectId(Long projectId) throws DaoException {
        Object[] parameters = {projectId};
        List<EmployeeRequirement> requirements = executor.executeQueryMultipleResult(
                ConstantDaoQuery.FIND_ALL_BY_PROJECT_ID_QUERY, parameters);
        return requirements;
    }

    @Override
    public EmployeeRequirement updateEmployeeRequirement(EmployeeRequirement requirement)
            throws DaoException {
        EmployeeRequirement resultRequirement = null;
        Object[] parameters = {requirement.getExperience(), requirement.getSalary(),
                requirement.getQualification(), requirement.getPrimarySkill(),
                requirement.getComment(), requirement.getId()};
        boolean result = executor.executeUpdate(
                ConstantDaoQuery.UPDATE_EMPLOYEE_REQUIREMENT_QUERY, parameters);
        if (result) {
            resultRequirement = requirement;
        }
        return resultRequirement;
    }

    @Override
    public boolean removeEmployeeRequirement(Long id) throws DaoException {
        Object[] parameters = {id};
        boolean removed = executor.executeUpdate(
                ConstantDaoQuery.DELETE_EMPLOYEE_REQUIREMENT_QUERY, parameters);
        return removed;
    }

}
