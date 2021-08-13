package by.voloshchuk.dao;

import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.DaoException;

public interface UserDetailDao {

    /**
     * Find required user data.
     *
     * @param userId - id of user with required user data
     * @return {@link UserDetail}
     */
    UserDetail findUserDetailByUserId(Long userId) throws DaoException;

    /**
     * Update user data.
     *
     * @param userDetail - user data for updating
     * @return {@link UserDetail}
     */
    UserDetail updateUserDetail(UserDetail userDetail) throws DaoException;

    /**
     * Update user detail status.
     *
     * @param id     - id of user detail to update
     * @param status - status to update
     * @return updated status
     */
    String updateUserDetailStatus(Long id, String status) throws DaoException;

    /**
     * Update stored user image path.
     *
     * @param userDetailId - id of user detail be to updated
     * @param imagePath    - path of image for updating
     * @return updated image path
     */
    String updateUserDetailImage(Long userDetailId, String imagePath) throws DaoException;

}
