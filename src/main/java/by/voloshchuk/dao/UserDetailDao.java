package by.voloshchuk.dao;

import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.DaoException;

public interface UserDetailDao {

    boolean addUserDetail(UserDetail userDetail) throws DaoException;

    UserDetail findUserDetailByUserId(Long userId) throws DaoException;

    UserDetail updateUserDetail(UserDetail userDetail) throws DaoException;

    String updateUserDetailStatus(Long id, String status) throws DaoException;

    String updateUserDetailImage(Long userDetailId, String imagePath) throws DaoException;

    boolean removeUserDetailById(Long id) throws DaoException;

}
