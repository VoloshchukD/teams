package by.voloshchuk.dao.impl;

import by.voloshchuk.dao.ConstantColumnName;
import by.voloshchuk.dao.ConstantDaoQuery;
import by.voloshchuk.dao.DaoExecutor;
import by.voloshchuk.dao.UserDao;
import by.voloshchuk.dao.builder.Builder;
import by.voloshchuk.dao.builder.UserBuilder;
import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.entity.User;
import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.DaoException;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    private CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();

    private final DaoExecutor<User> executor;

    public UserDaoImpl() {
        Builder<User> builder = new UserBuilder();
        executor = new DaoExecutor<>(builder);
    }

    public boolean addUser(User user) throws DaoException {
        boolean added = false;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                ConstantDaoQuery.ADD_USER_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            Object[] userParameters = {user.getEmail(), user.getPassword(),
                    user.getRole().toString(), user.getUserDetail().getId()};
            DaoExecutor.fillStatement(statement, userParameters);
            added = (statement.executeUpdate() == 1);
            if (added) {
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                long userId = resultSet.getInt(1);
                user.setId(userId);
                UserDetail userDetail = user.getUserDetail();
                try (PreparedStatement userDetailStatement = connection.prepareStatement(
                        ConstantDaoQuery.ADD_USER_DETAIL_QUERY,
                        Statement.RETURN_GENERATED_KEYS)) {
                    Object[] userDetailParameters = {userDetail.getFirstName(),
                            userDetail.getLastName(), userDetail.getCompany(),
                            userDetail.getPosition(), userDetail.getExperience(),
                            userDetail.getSalary(), userDetail.getPrimarySkill(),
                            userDetail.getSkillsDescription(), userDetail.getStatus().toString()};
                    DaoExecutor.fillStatement(userDetailStatement, userDetailParameters);
                    added = (userDetailStatement.executeUpdate() == 1);
                    if (added) {
                        resultSet = statement.getGeneratedKeys();
                        resultSet.next();
                        long userDetailsId = resultSet.getLong(1);
                        userDetail.setId(userDetailsId);
                        connection.commit();
                    }
                }
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                throw new DaoException(exception);
            } finally {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException exception) {
                    throw new DaoException(exception);
                }
            }
            throw new DaoException(e);
        }
        return added;
    }

    public Map<String, Integer> findBasicData() throws DaoException {
        Map<String, Integer> resultData = new HashMap<>();
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    ConstantDaoQuery.FIND_BASIC_DATA_QUERY);
            if (resultSet.next()) {
                resultData.put(ConstantColumnName.BASIC_DATA_YEARS,
                        resultSet.getInt(ConstantColumnName.BASIC_DATA_YEARS));
                resultData.put(ConstantColumnName.BASIC_DATA_PRODUCTIVITY,
                        resultSet.getInt(ConstantColumnName.BASIC_DATA_PRODUCTIVITY));
                resultData.put(ConstantColumnName.BASIC_DATA_CUSTOMERS_AMOUNT,
                        resultSet.getInt(ConstantColumnName.BASIC_DATA_CUSTOMERS_AMOUNT));
                resultData.put(ConstantColumnName.BASIC_DATA_PROJECTS,
                        resultSet.getInt(ConstantColumnName.BASIC_DATA_PROJECTS));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return resultData;
    }

    public User findUserById(Long id) throws DaoException {
        Object[] parameters = {id};
        User user = executor.executeQuery(
                ConstantDaoQuery.FIND_USER_BY_ID_QUERY, parameters);
        return user;
    }

    public List<User> findUsersByProjectId(Long projectId) throws DaoException {
        Object[] parameters = {projectId};
        List<User> users = executor.executeQueryMultipleResult(
                ConstantDaoQuery.FIND_USERS_BY_PROJECT_ID, parameters);
        return users;
    }

    public User findUserByEmail(String email) throws DaoException {
        Object[] parameters = {email};
        User user = executor.executeQuery(
                ConstantDaoQuery.FIND_USER_BY_EMAIL_QUERY, parameters);
        return user;
    }

    public List<User> findUsersByPrimarySkill(String primarySkill) throws DaoException {
        Object[] parameters = {ConstantDaoQuery.PERCENT
                + primarySkill
                + ConstantDaoQuery.PERCENT};
        List<User> users = executor.executeQueryMultipleResult(
                ConstantDaoQuery.FIND_USER_BY_PRIMARY_SKILL_QUERY, parameters);
        return users;
    }

    public User updateUser(User user) throws DaoException {
        User resultUser = null;
        Object[] parameters = {user.getEmail(), user.getPassword(), user.getId()};
        boolean result = executor.executeUpdate(
                ConstantDaoQuery.UPDATE_USER_QUERY, parameters);
        if (result) {
            resultUser = user;
        }
        return resultUser;
    }

    public boolean removeUserById(Long userId, Long userDetailId) throws DaoException {
        String[] queries = {ConstantDaoQuery.DELETE_USER_QUERY,
                ConstantDaoQuery.DELETE_USER_DETAIL_QUERY};
        Object[][] parameters = {{userId}, {userDetailId}};
        boolean removed = executor.executeUpdateTransactionMultiple(queries, parameters);
        return removed;
    }

    public List<User> findAllByEmployeeRequirement(EmployeeRequirement requirements)
            throws DaoException {
        Object[] parameters = {requirements.getExperience(), requirements.getSalary(),
                requirements.getPrimarySkill()};
        List<User> users = executor.executeQueryMultipleResult(
                ConstantDaoQuery.FIND_USER_BY_REQUIREMENT_QUERY, parameters);
        return users;
    }

    public boolean addUserToProject(Long userId, Long projectId) throws DaoException {
        Object[] parameters = {projectId, userId};
        boolean added = executor.executeUpdate(
                ConstantDaoQuery.ADD_USER_TO_PROJECT_QUERY, parameters);
        return added;
    }

    public boolean removeUserFromProject(Long userId, Long projectId) throws DaoException {
        Object[] parameters = {projectId, userId};
        boolean removed = executor.executeUpdate(
                ConstantDaoQuery.DELETE_USER_FROM_PROJECT_QUERY, parameters);
        return removed;
    }

}
