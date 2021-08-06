package by.voloshchuk.dao.impl;

import by.voloshchuk.dao.EmployeeRequirementDao;
import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRequirementDaoImpl implements EmployeeRequirementDao {

    private static final String ADD_EMPLOYEE_REQUIREMENT_QUERY = "INSERT INTO employee_requirements (experience, " +
            "salary, qualification, primary_skill, comment, technical_task_id) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String FIND_ALL_BY_TECHNICAL_TASK_ID_QUERY = "SELECT * FROM teams.employee_requirements " +
            "WHERE teams.employee_requirements.technical_task_id = ?";

    private static final String FIND_ALL_BY_PROJECT_ID_QUERY = "SELECT * FROM teams.employee_requirements " +
            "INNER JOIN teams.technical_tasks " +
            "ON teams.employee_requirements.technical_task_id = teams.technical_tasks.technical_task_id " +
            "INNER JOIN teams.projects " +
            "ON teams.projects.technical_task_id = teams.technical_tasks.technical_task_id " +
            "WHERE teams.projects.project_id = ?";

    private static final String UPDATE_EMPLOYEE_REQUIREMENT_QUERY = "UPDATE employee_requirements SET experience = ?, " +
            "salary = ?, qualification = ?, primary_skill = ?, comment = ? WHERE employee_requirement_id = ?";

    private static final String DELETE_EMPLOYEE_REQUIREMENT_QUERY = "DELETE FROM employee_requirements " +
            "WHERE employee_requirement_id = ?";

    private CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();

    public boolean addEmployeeRequirement(EmployeeRequirement requirement) throws DaoException {
        boolean isAdded = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_EMPLOYEE_REQUIREMENT_QUERY)) {
            statement.setInt(1, requirement.getExperience());
            statement.setInt(2, requirement.getSalary());
            statement.setString(3, requirement.getQualification());
            statement.setString(4, requirement.getPrimarySkill());
            statement.setString(5, requirement.getComment());
            statement.setLong(6, requirement.getTechnicalTask().getId());
            isAdded = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isAdded;
    }

    public List<EmployeeRequirement> findAllByTechnicalTaskId(Long technicalTaskId) throws DaoException {
        List<EmployeeRequirement> requirements = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_TECHNICAL_TASK_ID_QUERY)) {
            statement.setLong(1, technicalTaskId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                EmployeeRequirement requirement = new EmployeeRequirement();
                requirement.setId(resultSet.getLong(ConstantColumnName.EMPLOYEE_REQUIREMENT_ID));
                requirement.setExperience(resultSet.getInt(ConstantColumnName.EMPLOYEE_REQUIREMENT_EXPERIENCE));
                requirement.setSalary(resultSet.getInt(ConstantColumnName.EMPLOYEE_REQUIREMENT_SALARY));
                requirement.setQualification(resultSet.getString(ConstantColumnName.EMPLOYEE_REQUIREMENT_QUALIFICATION));
                requirement.setPrimarySkill(resultSet.getString(ConstantColumnName.EMPLOYEE_REQUIREMENT_PRIMARY_SKILL));
                requirement.setComment(resultSet.getString(ConstantColumnName.EMPLOYEE_REQUIREMENT_COMMENT));
                requirements.add(requirement);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return requirements;
    }

    public List<EmployeeRequirement> findAllByProjectId(Long projectId) throws DaoException {
        List<EmployeeRequirement> requirements = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_PROJECT_ID_QUERY)) {
            statement.setLong(1, projectId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                EmployeeRequirement requirement = new EmployeeRequirement();
                requirement.setId(resultSet.getLong(ConstantColumnName.EMPLOYEE_REQUIREMENT_ID));
                requirement.setExperience(resultSet.getInt(ConstantColumnName.EMPLOYEE_REQUIREMENT_EXPERIENCE));
                requirement.setSalary(resultSet.getInt(ConstantColumnName.EMPLOYEE_REQUIREMENT_SALARY));
                requirement.setQualification(resultSet.getString(ConstantColumnName.EMPLOYEE_REQUIREMENT_QUALIFICATION));
                requirement.setPrimarySkill(resultSet.getString(ConstantColumnName.EMPLOYEE_REQUIREMENT_PRIMARY_SKILL));
                requirement.setComment(resultSet.getString(ConstantColumnName.EMPLOYEE_REQUIREMENT_COMMENT));
                requirements.add(requirement);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return requirements;
    }

    public EmployeeRequirement updateEmployeeRequirement(EmployeeRequirement requirement) throws DaoException {
        EmployeeRequirement resultRequirement = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE_REQUIREMENT_QUERY)) {
            statement.setInt(1, requirement.getExperience());
            statement.setInt(2, requirement.getSalary());
            statement.setString(3, requirement.getQualification());
            statement.setString(4, requirement.getPrimarySkill());
            statement.setString(5, requirement.getComment());
            statement.setLong(6, requirement.getId());
            int result = statement.executeUpdate();
            if (result == 1) {
                resultRequirement = requirement;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return resultRequirement;
    }

    public boolean removeUserDetailById(Long id) throws DaoException {
        boolean isRemoved = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE_REQUIREMENT_QUERY)) {
            statement.setLong(1, id);
            isRemoved = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isRemoved;
    }

}
