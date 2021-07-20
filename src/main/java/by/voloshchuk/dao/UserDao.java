package by.voloshchuk.dao;

import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.entity.User;
import by.voloshchuk.exception.DaoException;

import java.util.List;

public interface UserDao {

    boolean addUser(User user) throws DaoException;

    User findUserById(Long id) throws DaoException;

    User findUserByEmail(String email) throws DaoException;

    User updateUser(User user) throws DaoException;

    boolean removeUserById(Long id) throws DaoException;

    List<User> findAllByEmployeeRequirement(EmployeeRequirement requirements) throws DaoException;

    boolean addUserToProject(Long projectId, Long userId) throws DaoException;

    boolean removeUserFromProject(Long projectId, Long userId) throws DaoException;

}
