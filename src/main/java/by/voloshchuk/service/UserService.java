package by.voloshchuk.service;

import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.entity.User;
import by.voloshchuk.entity.dto.UserDto;
import by.voloshchuk.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * User adding logics.
     *
     * @param userDto - entity with required data for adding
     * @return boolean result of adding
     */
    boolean addUser(UserDto userDto) throws ServiceException;

    /**
     * User authorization logics.
     *
     * @param email    - data to find user
     * @param password - value to check users validity
     * @return {@link User}
     */
    User checkUser(String email, String password) throws ServiceException;

    /**
     * Project users finding logics.
     *
     * @param projectId - id of project with users
     * @param role      - required users role
     * @return list of {@link User}
     */
    List<User> findUsersByProjectIdAndRole(Long projectId, String role) throws ServiceException;

    /**
     * Basic data finding logics.
     *
     * @return map of founded data
     */
    Map<String, Integer> findBasicData() throws ServiceException;

    /**
     * Users finding logics by employee requirement.
     *
     * @param requirements - requirement data to find user
     * @param projectId - project id to avoid already employed users
     * @return list of {@link User}
     */
    List<User> findAllByEmployeeRequirement(EmployeeRequirement requirements, Long projectId)
            throws ServiceException;

    /**
     * Users finding logics by primary skill.
     *
     * @param primarySkill - primary skill data to find user
     * @param projectId - id of project to avoid already employed users
     * @return list of {@link User}
     */
    List<User> findUsersByPrimarySkill(String primarySkill, Long projectId) throws ServiceException;

    /**
     * User to project adding logics.
     *
     * @param userId    - id of user to add
     * @param projectId - id of project where to add
     * @return boolean result of adding
     */
    boolean addUserToProject(Long userId, Long projectId) throws ServiceException;

    /**
     * User from project removing logics.
     *
     * @param userId    - id of user to remove
     * @param projectId - id of project from where to remove
     * @return boolean result of deletion
     */
    boolean removeUserFromProject(Long userId, Long projectId) throws ServiceException;

}
