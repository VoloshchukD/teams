package by.voloshchuk.service;

import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.ServiceException;

public interface UserDetailService {

    UserDetail findUserDetailByUserId(Long userId) throws ServiceException;

}
