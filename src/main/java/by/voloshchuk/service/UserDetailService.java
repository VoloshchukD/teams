package by.voloshchuk.service;

import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.entity.dto.UserDto;
import by.voloshchuk.exception.ServiceException;

public interface UserDetailService {

    /**
     * User detail finding logics.
     *
     * @param userId - id of user with user detail
     * @return {@link UserDetail}
     */
    UserDetail findUserDetailByUserId(Long userId) throws ServiceException;

    /**
     * User detail data updating logics.
     *
     * @param userDto - data for user detail updating
     * @return {@link UserDetail}
     */
    UserDetail updateUserDetail(UserDto userDto) throws ServiceException;

    /**
     * User image updating logics.
     *
     * @param userDetailId - id of user detail to update
     * @param imagePath    - path of image for updating
     * @return updated image path
     */
    String updateUserDetailImage(Long userDetailId, String imagePath) throws ServiceException;

    /**
     * User detail status updating logics.
     *
     * @param id     - id of user detail to update
     * @param status - status to update
     * @return updated status
     */
    String updateUserDetailStatus(Long id, String status) throws ServiceException;

}
