package by.voloshchuk.service;

import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;

public interface UserDetailService {

    UserDetail findUserDetailByUserId(Long userId) throws ServiceException;

    UserDetail updateUserDetail(UserDetail userDetail) throws ServiceException;

    String updateUserDetailImage(Long userDetailId, String imagePath) throws ServiceException;

}
