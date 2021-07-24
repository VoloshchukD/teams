package by.voloshchuk.service.impl;

import by.voloshchuk.dao.UserDetailDao;
import by.voloshchuk.dao.impl.UserDetailDaoImpl;
import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.UserDetailService;

public class UserDetailServiceImpl implements UserDetailService {

    private UserDetailDao userDetailDao = new UserDetailDaoImpl();

    public UserDetail findUserDetailByUserId(Long userId) throws ServiceException {
        UserDetail userDetail = null;
        try {
            userDetail = userDetailDao.findUserDetailByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return userDetail;
    }

}
