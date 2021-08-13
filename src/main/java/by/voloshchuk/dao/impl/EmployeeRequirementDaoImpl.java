package by.voloshchuk.dao.impl;

import by.voloshchuk.dao.DaoExecutor;
import by.voloshchuk.dao.EmployeeRequirementDao;
import by.voloshchuk.dao.builder.Builder;
import by.voloshchuk.dao.builder.EmployeeRequirementBuilder;
import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.exception.DaoException;

import java.util.List;

public class EmployeeRequirementDaoImpl implements EmployeeRequirementDao {

    private static final String ADD_EMPLOYEE_REQUIREMENT_QUERY = "INSERT INTO employee_requirements (experience, " +
            "salary, qualification, primary_skill, comment, technical_task_id) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String FIND_ALL_BY_TECHNICAL_TASK_ID_QUERY = "SELECT * FROM employee_requirements " +
            "WHERE employee_requirements.technical_task_id = ?";

    private static final String FIND_ALL_BY_PROJECT_ID_QUERY = "SELECT * FROM employee_requirements " +
            "INNER JOIN technical_tasks " +
            "ON employee_requirements.technical_task_id = technical_tasks.technical_task_id " +
            "INNER JOIN projects " +
            "ON projects.technical_task_id = technical_tasks.technical_task_id " +
            "WHERE projects.project_id = ?";

    private static final String UPDATE_EMPLOYEE_REQUIREMENT_QUERY = "UPDATE employee_requirements " +
            "SET experience = ?, salary = ?, qualification = ?, primary_skill = ?, comment = ? " +
            "WHERE employee_requirement_id = ?";

    private static final String DELETE_EMPLOYEE_REQUIREMENT_QUERY = "DELETE FROM employee_requirements " +
            "WHERE employee_requirement_id = ?";

    private CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();

    private final DaoExecutor<EmployeeRequirement> executor;

    public EmployeeRequirementDaoImpl() {
        Builder<EmployeeRequirement> builder = new EmployeeRequirementBuilder();
        executor = new DaoExecutor<>(builder);
    }

    public boolean addEmployeeRequirement(EmployeeRequirement requirement) throws DaoException {
        Object[] parameters = {requirement.getExperience(), requirement.getSalary(),
                requirement.getQualification(), requirement.getPrimarySkill(),
                requirement.getComment(), requirement.getTechnicalTask().getId()};
        boolean added = executor.executeUpdate(ADD_EMPLOYEE_REQUIREMENT_QUERY, parameters);
        return added;
    }

    public List<EmployeeRequirement> findAllByTechnicalTaskId(Long technicalTaskId) throws DaoException {
        Object[] parameters = {technicalTaskId};
        List<EmployeeRequirement> requirements = executor.executeQueryMultipleResult(
                FIND_ALL_BY_TECHNICAL_TASK_ID_QUERY, parameters);
        return requirements;
    }

    public List<EmployeeRequirement> findAllByProjectId(Long projectId) throws DaoException {
        Object[] parameters = {projectId};
        List<EmployeeRequirement> requirements = executor.executeQueryMultipleResult(
                FIND_ALL_BY_PROJECT_ID_QUERY, parameters);
        return requirements;
    }

    public EmployeeRequirement updateEmployeeRequirement(EmployeeRequirement requirement) throws DaoException {
        EmployeeRequirement resultRequirement = null;
        Object[] parameters = {requirement.getExperience(), requirement.getSalary(),
                requirement.getQualification(), requirement.getPrimarySkill(),
                requirement.getComment(), requirement.getId()};
        boolean result = executor.executeUpdate(UPDATE_EMPLOYEE_REQUIREMENT_QUERY, parameters);
        if (result) {
            resultRequirement = requirement;
        }
        return resultRequirement;
    }

    public boolean removeEmployeeRequirement(Long id) throws DaoException {
        Object[] parameters = {id};
        boolean removed = executor.executeUpdate(DELETE_EMPLOYEE_REQUIREMENT_QUERY, parameters);
        return removed;
    }

}
