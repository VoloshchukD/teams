package by.voloshchuk.service.impl;

import by.voloshchuk.dao.DaoProvider;
import by.voloshchuk.dao.UserDetailDao;
import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.UserDetailService;

public class UserDetailServiceImpl implements UserDetailService {

    private static DaoProvider daoProvider = DaoProvider.getInstance();

    public UserDetail findUserDetailByUserId(Long userId) throws ServiceException {
        UserDetail userDetail = null;
        UserDetailDao userDetailDao = daoProvider.getUserDetailDao();
        try {
            userDetail = userDetailDao.findUserDetailByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return userDetail;
    }

    @Override
    public UserDetail updateUserDetail(UserDetail userDetail) throws ServiceException {
        UserDetailDao userDetailDao = daoProvider.getUserDetailDao();
        UserDetail resultUserDetail = null;
        try {
            resultUserDetail = userDetailDao.updateUserDetail(userDetail);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return resultUserDetail;
    }

    @Override
    public String updateUserDetailImage(Long userDetailId, String imagePath) throws ServiceException {
        UserDetailDao userDetailDao = daoProvider.getUserDetailDao();
        String resultImagePath = null;
        try {
            resultImagePath = userDetailDao.updateUserDetailImage(userDetailId, imagePath);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return resultImagePath;
    }

    @Override
    public String updateUserDetailStatus(Long id, String status) throws ServiceException {
        UserDetailDao userDetailDao = daoProvider.getUserDetailDao();
        String resultStatus = null;
        try {
            resultStatus = userDetailDao.updateUserDetailStatus(id, status);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return resultStatus;
    }

}
