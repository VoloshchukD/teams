package by.voloshchuk.dao.impl;

import by.voloshchuk.dao.UserDetailDao;
import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.DaoException;

import java.sql.*;

public class UserDetailDaoImpl implements UserDetailDao {

    private static final String ADD_USER_DETAIL_QUERY = "INSERT INTO user_details (first_name, " +
            "last_name, company, position, experience, salary, primary_skill, skills_description, status) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String FIND_USER_DETAIL_BY_USER_ID_QUERY = "SELECT * FROM user_details INNER JOIN users ON user_details.user_detail_id = users.user_detail_id WHERE users.user_id = ?";

    private static final String UPDATE_USER_DETAIL_QUERY = "UPDATE user_details SET first_name = ?, " +
            "last_name = ?, company = ?, position = ?, experience = ?, salary = ?, primary_skill = ?, " +
            "skills_description = ?, status = ? WHERE user_detail_id = ?";

    private static final String UPDATE_USER_DETAIL_STATUS_QUERY = "UPDATE user_details " +
            "SET status = ? WHERE user_detail_id = ?";

    private static final String UPDATE_USER_DETAIL_IMAGE_QUERY = "UPDATE user_details SET user_image_path = ? " +
            "WHERE user_detail_id = ?";

    private static final String DELETE_USER_DETAIL_QUERY = "DELETE FROM user_details WHERE user_detail_id = ?";

    private CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();

    public boolean addUserDetail(UserDetail userDetail) throws DaoException {
        boolean isAdded = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_USER_DETAIL_QUERY,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, userDetail.getFirstName());
            statement.setString(2, userDetail.getLastName());
            statement.setString(3, userDetail.getCompany());
            statement.setString(4, userDetail.getPosition());
            statement.setInt(5, userDetail.getExperience());
            statement.setInt(6, userDetail.getSalary());
            statement.setString(7, userDetail.getPrimarySkill());
            statement.setString(8, userDetail.getSkillsDescription());
            statement.setString(9, userDetail.getStatus().toString());
            isAdded = statement.executeUpdate() == 1;
            if (isAdded) {
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                long userDetailsId = resultSet.getInt(1);
                userDetail.setId(userDetailsId);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isAdded;
    }

    public UserDetail findUserDetailByUserId(Long userId) throws DaoException {
        UserDetail userDetail = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_DETAIL_BY_USER_ID_QUERY)) {
            statement.setString(1, String.valueOf(userId));
            ResultSet resultSet = statement.executeQuery();
            userDetail = new UserDetail();
            if (resultSet.next()) {
                userDetail.setId(resultSet.getLong(ConstantColumnName.USER_DETAIL_ID));
                userDetail.setFirstName(resultSet.getString(ConstantColumnName.USER_DETAIL_FIRST_NAME));
                userDetail.setLastName(resultSet.getString(ConstantColumnName.USER_DETAIL_LAST_NAME));
                userDetail.setCompany(resultSet.getString(ConstantColumnName.USER_DETAIL_COMPANY));
                userDetail.setPosition(resultSet.getString(ConstantColumnName.USER_DETAIL_POSITION));
                userDetail.setExperience(resultSet.getInt(ConstantColumnName.USER_DETAIL_EXPERIENCE));
                userDetail.setSalary(resultSet.getInt(ConstantColumnName.USER_DETAIL_SALARY));
                userDetail.setPrimarySkill(resultSet.getString(ConstantColumnName.USER_DETAIL_PRIMARY_SKILL));
                userDetail.setSkillsDescription(
                        resultSet.getString(ConstantColumnName.USER_DETAIL_SKILLS_DESCRIPTION));
                userDetail.setStatus(
                        UserDetail.Status.valueOf(resultSet.getString(ConstantColumnName.USER_DETAIL_STATUS)));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return userDetail;
    }

    public UserDetail updateUserDetail(UserDetail userDetail) throws DaoException {
        UserDetail resultUserDetail = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_DETAIL_QUERY)) {
            statement.setString(1, userDetail.getFirstName());
            statement.setString(2, userDetail.getLastName());
            statement.setString(3, userDetail.getCompany());
            statement.setString(4, userDetail.getPosition());
            statement.setInt(5, userDetail.getExperience());
            statement.setInt(6, userDetail.getSalary());
            statement.setString(7, userDetail.getPrimarySkill());
            statement.setString(8, userDetail.getSkillsDescription());
            statement.setString(9, userDetail.getStatus().toString());
            statement.setLong(10, userDetail.getId());
            int result = statement.executeUpdate();
            if (result == 1) {
                resultUserDetail = userDetail;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return resultUserDetail;
    }

    public String updateUserDetailStatus(Long id, String status) throws DaoException {
        String resultStatus = null;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_DETAIL_STATUS_QUERY)) {

            statement.setString(1, status);
            statement.setLong(2, id);
            int result = statement.executeUpdate();
            if (result == 1) {
                resultStatus = status;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return status;
    }

    public String updateUserDetailImage(Long userDetailId, String imagePath) throws DaoException {
        String resultImagePath = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_DETAIL_IMAGE_QUERY)) {
            statement.setString(1, imagePath);
            statement.setLong(2, userDetailId);
            int result = statement.executeUpdate();
            if (result == 1) {
                resultImagePath = imagePath;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return resultImagePath;
    }

    public boolean removeUserDetailById(Long id) throws DaoException {
        boolean isRemoved = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_DETAIL_QUERY)) {
            statement.setLong(1, id);
            isRemoved = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isRemoved;
    }

}
