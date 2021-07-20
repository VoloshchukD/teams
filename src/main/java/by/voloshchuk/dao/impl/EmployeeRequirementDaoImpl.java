package by.voloshchuk.dao.impl;

import by.voloshchuk.dao.EmployeeRequirementDao;
import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.entity.User;
import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRequirementDaoImpl implements EmployeeRequirementDao {

    private static final String SQL_ADD_EMPLOYEE_REQUIREMENT = "INSERT INTO employee_requirements (experience, " +
            "salary, qualification, primary_skill, comment, technical_task_id) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_FIND_ALL_BY_TECHNICAL_TASK_ID = "SELECT * FROM teams.employee_requirements " +
            "WHERE teams.employee_requirements.technical_task_id = ?";

    private static final String SQL_UPDATE_EMPLOYEE_REQUIREMENT = "UPDATE employee_requirements SET experience = ?, " +
            "salary = ?, qualification = ?, primary_skill = ?, comment = ? WHERE employee_requirement_id = ?";

    private static final String SQL_DELETE_EMPLOYEE_REQUIREMENT = "DELETE FROM employee_requirements " +
            "WHERE employee_requirement_id = ?";

    private CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();

    public boolean addEmployeeRequirement(EmployeeRequirement requirement) throws DaoException {
        boolean isAdded = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_EMPLOYEE_REQUIREMENT)) {
            statement.setString(1, String.valueOf(requirement.getExperience()));
            statement.setString(2, String.valueOf(requirement.getSalary()));
            statement.setString(3, requirement.getQualification());
            statement.setString(4, requirement.getPrimarySkill());
            statement.setString(5, requirement.getComment());
            statement.setString(6, String.valueOf(requirement.getTechnicalTask().getId()));
            isAdded = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isAdded;
    }

    public List<EmployeeRequirement> findAllByTechnicalTaskId(Long technicalTaskId) throws DaoException {
        List<EmployeeRequirement> requirements = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_BY_TECHNICAL_TASK_ID)) {
            statement.setLong(1, technicalTaskId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                EmployeeRequirement requirement = new EmployeeRequirement();
                requirement.setId(Long.valueOf(resultSet.getString(ConstantColumnName.EMPLOYEE_REQUIREMENT_ID)));
                requirement.setExperience(Integer.valueOf(resultSet.getString(ConstantColumnName.EMPLOYEE_REQUIREMENT_EXPERIENCE)));
                requirement.setSalary(Integer.valueOf(resultSet.getString(ConstantColumnName.EMPLOYEE_REQUIREMENT_SALARY)));
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
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_EMPLOYEE_REQUIREMENT)) {
            statement.setString(1, String.valueOf(requirement.getExperience()));
            statement.setString(2, String.valueOf(requirement.getSalary()));
            statement.setString(3, requirement.getQualification());
            statement.setString(4, requirement.getPrimarySkill());
            statement.setString(5, requirement.getComment());
            statement.setString(6, String.valueOf(requirement.getId()));
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
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_EMPLOYEE_REQUIREMENT)) {
            statement.setString(1, String.valueOf(id));
            isRemoved = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isRemoved;
    }

}
