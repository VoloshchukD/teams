package by.voloshchuk.dao;

import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.entity.User;
import by.voloshchuk.exception.DaoException;

import java.util.List;
import java.util.Map;

public interface UserDao {

    /**
     * User data saving to storage.
     *
     * @param user - entity with data to save
     * @return boolean result of save
     */
    boolean addUser(User user) throws DaoException;

    /**
     * Find required data from data storage.
     *
     * @return map with founded data
     */
    Map<String, Integer> findBasicData() throws DaoException;

    /**
     * Find user by identifier.
     *
     * @param id - id of required user
     * @return {@link User}
     */
    User findUserById(Long id) throws DaoException;

    /**
     * Find all users associated with project.
     *
     * @param projectId - id of project with required users
     * @return list of {@link User}
     */
    List<User> findUsersByProjectId(Long projectId) throws DaoException;

    /**
     * Find user with unique email.
     *
     * @param email - email to find user
     * @return {@link User}
     */
    User findUserByEmail(String email) throws DaoException;

    /**
     * Update user data.
     *
     * @param user - user data for updating
     * @return {@link User}
     */
    User updateUser(User user) throws DaoException;

    /**
     * Delete user data from project.
     *
     * @param id           - id of user to be removed
     * @param userDetailId - id of user data associated with user
     * @return boolean result of deletion
     */
    boolean removeUserById(Long id, Long userDetailId) throws DaoException;

    /**
     * Find users that suit requirement data.
     *
     * @param requirements - requirement data to find user
     * @return list of {@link User}
     */
    List<User> findAllByEmployeeRequirement(EmployeeRequirement requirements) throws DaoException;

    /**
     * Find users with matching primary skill.
     *
     * @param primarySkill - primary skill data to find user
     * @return list of {@link User}
     */
    List<User> findUsersByPrimarySkill(String primarySkill) throws DaoException;

    /**
     * Associate user id with project id.
     *
     * @param userId    - id of user to add
     * @param projectId - id of project where to add
     * @return boolean result of adding
     */
    boolean addUserToProject(Long userId, Long projectId) throws DaoException;

    /**
     * Delete associated user id and project id.
     *
     * @param userId    - id of user to be removed
     * @param projectId - id of project to be removed
     * @return boolean result of deletion
     */
    boolean removeUserFromProject(Long userId, Long projectId) throws DaoException;

}
