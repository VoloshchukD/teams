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

    private final CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();

    private final DaoExecutor<User> executor;

    public UserDaoImpl() {
        Builder<User> builder = new UserBuilder();
        executor = new DaoExecutor<>(builder);
    }

    @Override
    public boolean addUser(User user) throws DaoException {
        boolean added = false;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement userDetailStatement = connection.prepareStatement(
                ConstantDaoQuery.ADD_USER_DETAIL_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            UserDetail userDetail = user.getUserDetail();
            Object[] userDetailParameters = {userDetail.getFirstName(),
                    userDetail.getLastName(), userDetail.getCompany(),
                    userDetail.getPosition(), userDetail.getExperience(),
                    userDetail.getSalary(), userDetail.getPrimarySkill(),
                    userDetail.getSkillsDescription(), userDetail.getStatus().toString()};
            DaoExecutor.fillStatement(userDetailStatement, userDetailParameters);
            added = (userDetailStatement.executeUpdate() == 1);
            if (added) {
                ResultSet resultSet = userDetailStatement.getGeneratedKeys();
                resultSet.next();
                long userDetailsId = resultSet.getLong(1);
                userDetail.setId(userDetailsId);
                try (PreparedStatement userStatement = connection.prepareStatement(
                        ConstantDaoQuery.ADD_USER_QUERY , Statement.RETURN_GENERATED_KEYS)) {
                    Object[] userParameters = {user.getEmail(), user.getPassword(),
                            user.getRole().toString(), user.getUserDetail().getId()};
                    DaoExecutor.fillStatement(userStatement, userParameters);
                    added = (userStatement.executeUpdate() == 1);
                    if (added) {
                        resultSet = userStatement.getGeneratedKeys();
                        resultSet.next();
                        long userId = resultSet.getInt(1);
                        user.setId(userId);
                        connection.commit();
                    }
                }
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                throw new DaoException("Exception while add user rollback ", e);
            }
            throw new DaoException("Exception while add user ", e);
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                throw new DaoException("Exception while connection close ", e);
            }
        }
        return added;
    }

    @Override
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
            throw new DaoException("Exception while find basic data ", e);
        }
        return resultData;
    }

    @Override
    public User findUserById(Long id) throws DaoException {
        Object[] parameters = {id};
        User user = executor.executeQuery(
                ConstantDaoQuery.FIND_USER_BY_ID_QUERY, parameters);
        return user;
    }

    @Override
    public List<User> findUsersByProjectIdAndRole(Long projectId, String role) throws DaoException {
        Object[] parameters = {projectId, role, role};
        List<User> users = executor.executeQueryMultipleResult(
                ConstantDaoQuery.FIND_USERS_BY_PROJECT_ID, parameters);
        return users;
    }

    @Override
    public User findUserByEmail(String email) throws DaoException {
        Object[] parameters = {email};
        User user = executor.executeQuery(
                ConstantDaoQuery.FIND_USER_BY_EMAIL_QUERY, parameters);
        return user;
    }

    @Override
    public List<User> findUsersByPrimarySkill(String primarySkill, Long projectId)
            throws DaoException {
        Object[] parameters = {projectId, ConstantDaoQuery.PERCENT
                + primarySkill
                + ConstantDaoQuery.PERCENT};
        List<User> users = executor.executeQueryMultipleResult(
                ConstantDaoQuery.FIND_USER_BY_PRIMARY_SKILL_QUERY, parameters);
        return users;
    }

    @Override
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

    @Override
    public boolean removeUserById(Long userId, Long userDetailId) throws DaoException {
        String[] queries = {ConstantDaoQuery.DELETE_USER_QUERY,
                ConstantDaoQuery.DELETE_USER_DETAIL_QUERY};
        Object[][] parameters = {{userId}, {userDetailId}};
        boolean removed = executor.executeUpdateTransactionMultiple(queries, parameters);
        return removed;
    }

    @Override
    public List<User> findAllByEmployeeRequirement(EmployeeRequirement requirements, Long projectId)
            throws DaoException {
        Object[] parameters = {projectId, requirements.getExperience(), requirements.getSalary(),
                requirements.getPrimarySkill()};
        List<User> users = executor.executeQueryMultipleResult(
                ConstantDaoQuery.FIND_USER_BY_REQUIREMENT_QUERY, parameters);
        return users;
    }

    @Override
    public boolean addUserToProject(Long userId, Long projectId) throws DaoException {
        Object[] parameters = {projectId, userId};
        boolean added = executor.executeUpdate(
                ConstantDaoQuery.ADD_USER_TO_PROJECT_QUERY, parameters);
        return added;
    }

    @Override
    public boolean removeUserFromProject(Long userId, Long projectId) throws DaoException {
        Object[] parameters = {projectId, userId};
        boolean removed = executor.executeUpdate(
                ConstantDaoQuery.DELETE_USER_FROM_PROJECT_QUERY, parameters);
        return removed;
    }

}
