package by.voloshchuk.dao.impl;

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

    private static final String ADD_USER_QUERY = "INSERT INTO users (email, " +
            "password, role, user_detail_id) VALUES (?, ?, ?, ?)";

    private static final String ADD_USER_DETAIL_QUERY = "INSERT INTO user_details (first_name, " +
            "last_name, company, position, experience, salary, primary_skill, skills_description, status) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String ADD_USER_TO_PROJECT_QUERY = "INSERT INTO user_project_maps (project_id, user_id) " +
            "VALUES (?, ?);";

    private static final String DELETE_USER_FROM_PROJECT_QUERY = "DELETE FROM user_project_maps " +
            "WHERE project_id = ? AND user_id = ?;";

    private static final String FIND_BASIC_DATA_QUERY = "SELECT " +
            "TIMESTAMPDIFF(year, MIN(projects.start_date), CURDATE()) " +
            "AS years, ROUND(AVG(TIMESTAMPDIFF(month, projects.start_date, technical_tasks.deadline))) " +
            "AS productivity, COUNT(DISTINCT(users.user_id)) AS customers, COUNT(projects.project_id) " +
            "AS projects FROM users INNER JOIN technical_tasks " +
            "ON users.user_id = technical_tasks.customer_id INNER JOIN projects " +
            "ON technical_tasks.technical_task_id = projects.technical_task_id " +
            "WHERE users.role = 'CUSTOMER' AND projects.state = 'FINISHED'";

    private static final String FIND_USER_BY_ID_QUERY = "SELECT * FROM users INNER JOIN user_details " +
            "ON users.user_detail_id = user_details.user_detail_id " +
            "WHERE user_id = ?";

    private static final String FIND_USERS_BY_PROJECT_ID = "SELECT * FROM users " +
            "INNER JOIN user_project_maps " +
            "ON users.user_id = user_project_maps.user_id " +
            "INNER JOIN user_details ON users.user_id = user_details.user_detail_id " +
            "WHERE user_project_maps.project_id = ?";

    private static final String FIND_USER_BY_EMAIL_QUERY = "SELECT * FROM users INNER JOIN user_details " +
            "ON users.user_detail_id = user_details.user_detail_id " +
            "WHERE email = ?";

    private static final String FIND_USER_BY_REQUIREMENT_QUERY = "SELECT * FROM users " +
            "INNER JOIN user_details ON users.user_id = user_details.user_detail_id WHERE users.role = 'DEVELOPER' " +
            "AND user_details.experience >= ? AND user_details.salary <= ? AND user_details.primary_skill = ? " +
            "AND user_details.status = 'NOT_BUSY'";

    private static final String FIND_USER_BY_PRIMARY_SKILL_QUERY = "SELECT * FROM user_details " +
            "INNER JOIN users ON users.user_detail_id = user_details.user_detail_id " +
            "WHERE user_details.primary_skill LIKE ?";

    private static final String PERCENT = "%";

    private static final String UPDATE_USER_QUERY = "UPDATE users SET email = ?, " +
            "password = ? WHERE user_id = ?";

    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE user_id = ?";

    private static final String DELETE_USER_DETAIL_QUERY = "DELETE FROM user_details WHERE user_detail_id = ?";

    private CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();

    private final DaoExecutor<User> executor;

    public UserDaoImpl() {
        Builder<User> builder = new UserBuilder();
        executor = new DaoExecutor<>(builder);
    }

    public boolean addUser(User user) throws DaoException {
        boolean added = false;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ADD_USER_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole().toString());
            statement.setLong(4, user.getUserDetail().getId());
            added = (statement.executeUpdate() == 1);
            if (added) {
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                long userId = resultSet.getInt(1);
                user.setId(userId);
                UserDetail userDetail = user.getUserDetail();
                try (PreparedStatement userDetailStatement = connection.prepareStatement(ADD_USER_DETAIL_QUERY,
                        Statement.RETURN_GENERATED_KEYS)) {
                    userDetailStatement.setString(1, userDetail.getFirstName());
                    userDetailStatement.setString(2, userDetail.getLastName());
                    userDetailStatement.setString(3, userDetail.getCompany());
                    userDetailStatement.setString(4, userDetail.getPosition());
                    userDetailStatement.setInt(5, userDetail.getExperience());
                    userDetailStatement.setInt(6, userDetail.getSalary());
                    userDetailStatement.setString(7, userDetail.getPrimarySkill());
                    userDetailStatement.setString(8, userDetail.getSkillsDescription());
                    userDetailStatement.setString(9, userDetail.getStatus().toString());
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
            ResultSet resultSet = statement.executeQuery(FIND_BASIC_DATA_QUERY);
            if (resultSet.next()) {
                resultData.put(ConstantColumnName.BASIC_DATA_YEARS, resultSet.getInt(
                        ConstantColumnName.BASIC_DATA_YEARS));
                resultData.put(ConstantColumnName.BASIC_DATA_PRODUCTIVITY, resultSet.getInt(
                        ConstantColumnName.BASIC_DATA_PRODUCTIVITY));
                resultData.put(ConstantColumnName.BASIC_DATA_CUSTOMERS_AMOUNT, resultSet.getInt(
                        ConstantColumnName.BASIC_DATA_CUSTOMERS_AMOUNT));
                resultData.put(ConstantColumnName.BASIC_DATA_PROJECTS, resultSet.getInt(
                        ConstantColumnName.BASIC_DATA_PROJECTS));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return resultData;
    }

    public User findUserById(Long id) throws DaoException {
        Object[] parameters = {id};
        User user = executor.executeQuery(FIND_USER_BY_ID_QUERY, parameters);
        return user;
    }

    public List<User> findUsersByProjectId(Long projectId) throws DaoException {
        Object[] parameters = {projectId};
        List<User> users = executor.executeQueryMultipleResult(FIND_USERS_BY_PROJECT_ID, parameters);
        return users;
    }

    public User findUserByEmail(String email) throws DaoException {
        Object[] parameters = {email};
        User user = executor.executeQuery(FIND_USER_BY_EMAIL_QUERY, parameters);
        return user;
    }

    public List<User> findUsersByPrimarySkill(String primarySkill) throws DaoException {
        Object[] parameters = {PERCENT + primarySkill + PERCENT};
        List<User> users = executor.executeQueryMultipleResult(FIND_USER_BY_PRIMARY_SKILL_QUERY, parameters);
        return users;
    }

    public User updateUser(User user) throws DaoException {
        User resultUser = null;
        Object[] parameters = {user.getEmail(), user.getPassword(), user.getId()};
        boolean result = executor.executeUpdate(UPDATE_USER_QUERY, parameters);
        if (result) {
            resultUser = user;
        }
        return resultUser;
    }

    public boolean removeUserById(Long userId, Long userDetailId) throws DaoException {
        boolean removed = false;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER_QUERY)) {
            connection.setAutoCommit(false);
            statement.setLong(1, userId);
            removed = (statement.executeUpdate() == 1);
            if (removed) {
                try (PreparedStatement userDetailStatement = connection.prepareStatement(
                        DELETE_USER_DETAIL_QUERY)) {
                    userDetailStatement.setLong(1, userDetailId);
                    removed = (userDetailStatement.executeUpdate() == 1);
                }
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                throw new DaoException(exception);
            }
            throw new DaoException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException exception) {
                throw new DaoException(exception);
            }
        }
        return removed;
    }

    public List<User> findAllByEmployeeRequirement(EmployeeRequirement requirements) throws DaoException {
        Object[] parameters = {requirements.getExperience(), requirements.getSalary(),
                requirements.getPrimarySkill()};
        List<User> users = executor.executeQueryMultipleResult(FIND_USER_BY_REQUIREMENT_QUERY, parameters);
        return users;
    }

    public boolean addUserToProject(Long userId, Long projectId) throws DaoException {
        Object[] parameters = {projectId, userId};
        boolean added = executor.executeUpdate(ADD_USER_TO_PROJECT_QUERY, parameters);
        return added;
    }

    public boolean removeUserFromProject(Long userId, Long projectId) throws DaoException {
        Object[] parameters = {projectId, userId};
        boolean removed = executor.executeUpdate(DELETE_USER_FROM_PROJECT_QUERY, parameters);
        return removed;
    }

}
