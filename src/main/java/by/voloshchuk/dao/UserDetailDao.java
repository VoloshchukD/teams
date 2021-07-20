package by.voloshchuk.dao;

import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.DaoException;

public interface UserDetailDao {

    boolean addUserDetail(UserDetail userDetail) throws DaoException;

}
