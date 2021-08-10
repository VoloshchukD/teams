package by.voloshchuk.service;

import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.entity.User;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface UserService {

    boolean addUser(User user) throws ServiceException;

    User checkUser(String email, String password) throws ServiceException;

    List<User> findUsersByProjectId(Long projectId) throws ServiceException;

    Map<String, Integer> findBasicData() throws ServiceException;

    List<User> findAllByEmployeeRequirement(EmployeeRequirement requirements) throws ServiceException;

    List<User> findUsersByPrimarySkill(String primarySkill) throws ServiceException;

    boolean addUserToProject(Long userId, Long projectId) throws ServiceException;

    boolean removeUserFromProject(Long userId, Long projectId) throws ServiceException;

}
