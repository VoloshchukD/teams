package by.voloshchuk.dao.impl;

import by.voloshchuk.dao.UserDao;
import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.entity.User;
import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final String SQL_ADD_USER = "INSERT INTO users (email, " +
            "password, role, user_detail_id) VALUES (?, ?, ?, ?)";

    private static final String SQL_ADD_USER_TO_PROJECT = "INSERT INTO teams.user_project_maps (project_id, user_id) VALUES (?, ?);";

    private static final String SQL_DELETE_USER_FROM_PROJECT = "DELETE FROM teams.user_project_maps WHERE project_id = ? AND user_id = ?;";

    private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users INNER JOIN user_details " +
            "ON users.user_detail_id = user_details.user_detail_id " +
            "WHERE user_id = ?";

    private static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM users INNER JOIN user_details " +
            "ON users.user_detail_id = user_details.user_detail_id " +
            "WHERE email = ?";

    private static final String SQL_FIND_USER_BY_REQUIREMENT = "SELECT * FROM users " +
            "INNER JOIN user_details ON users.user_id = user_details.user_detail_id WHERE teams.users.role = 'developer' " +
            "AND teams.user_details.experience >= ? AND teams.user_details.salary <= ? AND teams.user_details.primary_skill = ?";

    private static final String SQL_UPDATE_USER = "UPDATE users SET email = ?, " +
            "password = ? WHERE user_id = ?";

    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE user_id = ?";

    private CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();

    public boolean addUser(User user) throws DaoException {
        boolean isAdded = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER)) {
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

    public User findUserById(Long id) throws DaoException {
        User user = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();
            user = new User();
            if (resultSet.next()) {
                user.setId(Long.valueOf(resultSet.getString(ConstantColumnName.USER_ID)));
                user.setEmail(resultSet.getString(ConstantColumnName.USER_EMAIL));
                user.setPassword(resultSet.getString(ConstantColumnName.USER_PASSWORD));
                user.setRole(resultSet.getString(ConstantColumnName.USER_ROLE));

                UserDetail userDetail = new UserDetail();
                userDetail.setId(Long.valueOf(resultSet.getString(ConstantColumnName.USER_DETAIL_ID)));
                userDetail.setFirstName(resultSet.getString(ConstantColumnName.USER_DETAIL_FIRST_NAME));
                userDetail.setLastName(resultSet.getString(ConstantColumnName.USER_DETAIL_LAST_NAME));
                userDetail.setCompany(resultSet.getString(ConstantColumnName.USER_DETAIL_COMPANY));
                userDetail.setPosition(resultSet.getString(ConstantColumnName.USER_DETAIL_POSITION));
                userDetail.setExperience(Integer.parseInt(resultSet.getString(ConstantColumnName.USER_DETAIL_EXPERIENCE)));
                userDetail.setSalary(Integer.parseInt(resultSet.getString(ConstantColumnName.USER_DETAIL_SALARY)));
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
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            user = new User();
            if (resultSet.next()) {
                user.setId(Long.valueOf(resultSet.getString(ConstantColumnName.USER_ID)));
                user.setEmail(resultSet.getString(ConstantColumnName.USER_EMAIL));
                user.setPassword(resultSet.getString(ConstantColumnName.USER_PASSWORD));
                user.setRole(resultSet.getString(ConstantColumnName.USER_ROLE));

                UserDetail userDetail = new UserDetail();
                userDetail.setId(Long.valueOf(resultSet.getString(ConstantColumnName.USER_DETAIL_ID)));
                userDetail.setFirstName(resultSet.getString(ConstantColumnName.USER_DETAIL_FIRST_NAME));
                userDetail.setLastName(resultSet.getString(ConstantColumnName.USER_DETAIL_LAST_NAME));
                userDetail.setCompany(resultSet.getString(ConstantColumnName.USER_DETAIL_COMPANY));
                userDetail.setPosition(resultSet.getString(ConstantColumnName.USER_DETAIL_POSITION));
                userDetail.setExperience(Integer.parseInt(resultSet.getString(ConstantColumnName.USER_DETAIL_EXPERIENCE)));
                userDetail.setSalary(Integer.parseInt(resultSet.getString(ConstantColumnName.USER_DETAIL_SALARY)));
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

    public User updateUser(User user) throws DaoException {
        User resultUser = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)) {
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
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER)) {
            User user = findUserById(id);
            statement.setString(1, String.valueOf(id));
            isRemoved = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isRemoved;
    }

    public List<User> findAllByEmployeeRequirement(EmployeeRequirement requirements) throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_REQUIREMENT)) {
            statement.setString(1, String.valueOf(requirements.getExperience()));
            statement.setString(2, String.valueOf(requirements.getSalary()));
            statement.setString(3, String.valueOf(requirements.getPrimarySkill()));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                UserDetail userDetail = new UserDetail();

                userDetail.setId(Long.valueOf(resultSet.getString(ConstantColumnName.USER_DETAIL_ID)));
                userDetail.setFirstName(resultSet.getString(ConstantColumnName.USER_DETAIL_FIRST_NAME));
                userDetail.setLastName(resultSet.getString(ConstantColumnName.USER_DETAIL_LAST_NAME));
                userDetail.setCompany(resultSet.getString(ConstantColumnName.USER_DETAIL_COMPANY));
                userDetail.setPosition(resultSet.getString(ConstantColumnName.USER_DETAIL_POSITION));
                userDetail.setExperience(Integer.parseInt(resultSet.getString(ConstantColumnName.USER_DETAIL_EXPERIENCE)));
                userDetail.setSalary(Integer.parseInt(resultSet.getString(ConstantColumnName.USER_DETAIL_SALARY)));
                userDetail.setStatus(resultSet.getString(ConstantColumnName.USER_DETAIL_STATUS));

                user.setId(Long.valueOf(resultSet.getString(ConstantColumnName.USER_ID)));
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
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER_TO_PROJECT)) {
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
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER_FROM_PROJECT)) {
            statement.setLong(1, projectId);
            statement.setLong(2, userId);
            isRemoved = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isRemoved;
    }

}
