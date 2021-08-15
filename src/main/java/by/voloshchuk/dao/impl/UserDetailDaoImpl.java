package by.voloshchuk.dao.impl;

import by.voloshchuk.dao.ConstantDaoQuery;
import by.voloshchuk.dao.DaoExecutor;
import by.voloshchuk.dao.UserDetailDao;
import by.voloshchuk.dao.builder.Builder;
import by.voloshchuk.dao.builder.UserDetailBuilder;
import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.DaoException;

public class UserDetailDaoImpl implements UserDetailDao {

    private final DaoExecutor<UserDetail> executor;

    public UserDetailDaoImpl() {
        Builder<UserDetail> builder = new UserDetailBuilder();
        executor = new DaoExecutor<>(builder);
    }

    public UserDetail findUserDetailByUserId(Long userId) throws DaoException {
        Object[] parameters = {userId};
        UserDetail userDetail = executor.executeQuery(
                ConstantDaoQuery.FIND_USER_DETAIL_BY_USER_ID_QUERY, parameters);
        return userDetail;
    }

    public UserDetail updateUserDetail(UserDetail userDetail) throws DaoException {
        UserDetail resultUserDetail = null;
        Object[] parameters = {userDetail.getFirstName(), userDetail.getLastName(),
                userDetail.getCompany(), userDetail.getPosition(),
                userDetail.getExperience(), userDetail.getSalary(),
                userDetail.getPrimarySkill(), userDetail.getSkillsDescription(),
                userDetail.getStatus().toString(), userDetail.getId()};
        boolean result = executor.executeUpdate(
                ConstantDaoQuery.UPDATE_USER_DETAIL_QUERY, parameters);
        if (result) {
            resultUserDetail = userDetail;
        }
        return resultUserDetail;
    }

    public String updateUserDetailStatus(Long id, String status) throws DaoException {
        String resultStatus = null;
        Object[] parameters = {status, id};
        boolean result = executor.executeUpdate(
                ConstantDaoQuery.UPDATE_USER_DETAIL_STATUS_QUERY, parameters);
        if (result) {
            resultStatus = status;
        }
        return status;
    }

    public String updateUserDetailImage(Long userDetailId, String imagePath)
            throws DaoException {
        String resultImagePath = null;
        Object[] parameters = {imagePath, userDetailId};
        boolean result = executor.executeUpdate(
                ConstantDaoQuery.UPDATE_USER_DETAIL_IMAGE_QUERY, parameters);
        if (result) {
            resultImagePath = imagePath;
        }
        return resultImagePath;
    }

}
