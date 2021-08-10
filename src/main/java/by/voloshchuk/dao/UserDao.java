package by.voloshchuk.dao;

import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.entity.User;
import by.voloshchuk.exception.DaoException;

import java.util.List;
import java.util.Map;

public interface UserDao {

    boolean addUser(User user) throws DaoException;

    Map<String, Integer> findBasicData() throws DaoException;

    User findUserById(Long id) throws DaoException;

    List<User> findUsersByProjectId(Long projectId) throws DaoException;

    User findUserByEmail(String email) throws DaoException;

    User updateUser(User user) throws DaoException;

    boolean removeUserById(Long id) throws DaoException;

    List<User> findAllByEmployeeRequirement(EmployeeRequirement requirements) throws DaoException;

    List<User> findUsersByPrimarySkill(String primarySkill) throws DaoException;

    boolean addUserToProject(Long userId, Long projectId) throws DaoException;

    boolean removeUserFromProject(Long userId, Long projectId) throws DaoException;

}
