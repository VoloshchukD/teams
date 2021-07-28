package by.voloshchuk.dao.impl;

import by.voloshchuk.dao.UserDao;
import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.entity.User;
import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    private static final String ADD_USER_QUERY = "INSERT INTO users (email, " +
            "password, role, user_detail_id) VALUES (?, ?, ?, ?)";

    private static final String ADD_USER_TO_PROJECT_QUERY = "INSERT INTO teams.user_project_maps (project_id, user_id) VALUES (?, ?);";

    private static final String DELETE_USER_FROM_PROJECT_QUERY = "DELETE FROM teams.user_project_maps WHERE project_id = ? AND user_id = ?;";

    private static final String FIND_BASIC_DATA_QUERY = "SELECT TIMESTAMPDIFF(year, MIN(teams.projects.start_date), CURDATE()) " +
            "AS years, ROUND(AVG(TIMESTAMPDIFF(month, teams.projects.start_date, teams.technical_tasks.deadline))) " +
            "AS productivity, COUNT(DISTINCT(teams.users.user_id)) AS customers, COUNT(teams.projects.project_id) " +
            "AS projects FROM teams.users INNER JOIN teams.technical_tasks " +
            "ON teams.users.user_id = teams.technical_tasks.customer_id INNER JOIN teams.projects " +
            "ON teams.technical_tasks.technical_task_id = teams.projects.technical_task_id " +
            "WHERE teams.users.role = 'customer' AND teams.projects.state = 'finished'";

    private static final String FIND_USER_BY_ID_QUERY = "SELECT * FROM users INNER JOIN user_details " +
            "ON users.user_detail_id = user_details.user_detail_id " +
            "WHERE user_id = ?";

    private static final String FIND_USER_BY_EMAIL_QUERY = "SELECT * FROM users INNER JOIN user_details " +
            "ON users.user_detail_id = user_details.user_detail_id " +
            "WHERE email = ?";

    private static final String FIND_USER_BY_REQUIREMENT_QUERY = "SELECT * FROM users " +
            "INNER JOIN user_details ON users.user_id = user_details.user_detail_id WHERE teams.users.role = 'developer' " +
            "AND teams.user_details.experience >= ? AND teams.user_details.salary <= ? AND teams.user_details.primary_skill = ?";

    private static final String UPDATE_USER_QUERY = "UPDATE users SET email = ?, " +
            "password = ? WHERE user_id = ?";

    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE user_id = ?";

    private CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();

    public boolean addUser(User user) throws DaoException {
        boolean isAdded = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_USER_QUERY)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.setString(4, String.valueOf(user.getUserDetail().getId()));
            isAdded = statement.executeUpdate() == 1;
            if (isAdded) {
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                long userId = resultSet.getInt(1);
                user.setId(userId);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isAdded;
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
        User user = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID_QUERY)) {
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();
            user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(ConstantColumnName.USER_ID));
                user.setEmail(resultSet.getString(ConstantColumnName.USER_EMAIL));
                user.setPassword(resultSet.getString(ConstantColumnName.USER_PASSWORD));
                user.setRole(resultSet.getString(ConstantColumnName.USER_ROLE));

                UserDetail userDetail = new UserDetail();
                userDetail.setId(resultSet.getLong(ConstantColumnName.USER_DETAIL_ID));
                userDetail.setImagePath(resultSet.getString(ConstantColumnName.USER_DETAIL_IMAGE));
                userDetail.setFirstName(resultSet.getString(ConstantColumnName.USER_DETAIL_FIRST_NAME));
                userDetail.setLastName(resultSet.getString(ConstantColumnName.USER_DETAIL_LAST_NAME));
                userDetail.setCompany(resultSet.getString(ConstantColumnName.USER_DETAIL_COMPANY));
                userDetail.setPosition(resultSet.getString(ConstantColumnName.USER_DETAIL_POSITION));
                userDetail.setExperience(resultSet.getInt(ConstantColumnName.USER_DETAIL_EXPERIENCE));
                userDetail.setSalary(resultSet.getInt(ConstantColumnName.USER_DETAIL_SALARY));
                userDetail.setPrimarySkill(resultSet.getString(ConstantColumnName.USER_DETAIL_PRIMARY_SKILL));
                userDetail.setSkillsDescription(resultSet.getString(ConstantColumnName.USER_DETAIL_SKILLS_DESCRIPTION));
                userDetail.setStatus(resultSet.getString(ConstantColumnName.USER_DETAIL_STATUS));
                user.setUserDetail(userDetail);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }

    public User findUserByEmail(String email) throws DaoException {
        User user = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL_QUERY)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(ConstantColumnName.USER_ID));
                user.setEmail(resultSet.getString(ConstantColumnName.USER_EMAIL));
                user.setPassword(resultSet.getString(ConstantColumnName.USER_PASSWORD));
                user.setRole(resultSet.getString(ConstantColumnName.USER_ROLE));

                UserDetail userDetail = new UserDetail();
                userDetail.setId(resultSet.getLong(ConstantColumnName.USER_DETAIL_ID));
                userDetail.setImagePath(resultSet.getString(ConstantColumnName.USER_DETAIL_IMAGE));
                userDetail.setFirstName(resultSet.getString(ConstantColumnName.USER_DETAIL_FIRST_NAME));
                userDetail.setLastName(resultSet.getString(ConstantColumnName.USER_DETAIL_LAST_NAME));
                userDetail.setCompany(resultSet.getString(ConstantColumnName.USER_DETAIL_COMPANY));
                userDetail.setPosition(resultSet.getString(ConstantColumnName.USER_DETAIL_POSITION));
                userDetail.setExperience(resultSet.getInt(ConstantColumnName.USER_DETAIL_EXPERIENCE));
                userDetail.setSalary(resultSet.getInt(ConstantColumnName.USER_DETAIL_SALARY));
                userDetail.setPrimarySkill(resultSet.getString(ConstantColumnName.USER_DETAIL_PRIMARY_SKILL));
                userDetail.setSkillsDescription(resultSet.getString(ConstantColumnName.USER_DETAIL_SKILLS_DESCRIPTION));
                userDetail.setStatus(resultSet.getString(ConstantColumnName.USER_DETAIL_STATUS));
                userDetail.setImagePath(resultSet.getString(ConstantColumnName.USER_DETAIL_IMAGE));
                user.setUserDetail(userDetail);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }

    public User updateUser(User user) throws DaoException {
        User resultUser = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setLong(3, user.getId());
            int result = statement.executeUpdate();
            if (result == 1) {
                resultUser = user;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return resultUser;
    }

    public boolean removeUserById(Long id) throws DaoException {
        boolean isRemoved = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_QUERY)) {
            User user = findUserById(id);
            statement.setLong(1, id);
            isRemoved = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isRemoved;
    }

    public List<User> findAllByEmployeeRequirement(EmployeeRequirement requirements) throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_REQUIREMENT_QUERY)) {
            statement.setLong(1, requirements.getExperience());
            statement.setInt(2, requirements.getSalary());
            statement.setString(3, requirements.getPrimarySkill());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                UserDetail userDetail = new UserDetail();

                userDetail.setId(resultSet.getLong(ConstantColumnName.USER_DETAIL_ID));
                userDetail.setFirstName(resultSet.getString(ConstantColumnName.USER_DETAIL_FIRST_NAME));
                userDetail.setLastName(resultSet.getString(ConstantColumnName.USER_DETAIL_LAST_NAME));
                userDetail.setCompany(resultSet.getString(ConstantColumnName.USER_DETAIL_COMPANY));
                userDetail.setPosition(resultSet.getString(ConstantColumnName.USER_DETAIL_POSITION));
                userDetail.setExperience(resultSet.getInt(ConstantColumnName.USER_DETAIL_EXPERIENCE));
                userDetail.setSalary(resultSet.getInt(ConstantColumnName.USER_DETAIL_SALARY));
                userDetail.setStatus(resultSet.getString(ConstantColumnName.USER_DETAIL_STATUS));

                user.setId(resultSet.getLong(ConstantColumnName.USER_ID));
                user.setEmail(resultSet.getString(ConstantColumnName.USER_EMAIL));
                user.setPassword(resultSet.getString(ConstantColumnName.USER_PASSWORD));
                user.setRole(resultSet.getString(ConstantColumnName.USER_ROLE));
                user.setUserDetail(userDetail);

                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }

    public boolean addUserToProject(Long projectId, Long userId) throws DaoException {
        boolean isAdded = false;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_USER_TO_PROJECT_QUERY)) {
            statement.setLong(1, projectId);
            statement.setLong(2, userId);
            isAdded = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isAdded;
    }

    public boolean removeUserFromProject(Long projectId, Long userId) throws DaoException {
        boolean isRemoved = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_FROM_PROJECT_QUERY)) {
            statement.setLong(1, projectId);
            statement.setLong(2, userId);
            isRemoved = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isRemoved;
    }

}
