package by.voloshchuk.service;

import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.entity.dto.UserDto;
import by.voloshchuk.exception.ServiceException;

public interface UserDetailService {

    UserDetail findUserDetailByUserId(Long userId) throws ServiceException;

    UserDetail updateUserDetail(UserDto userDto) throws ServiceException;

    String updateUserDetailImage(Long userDetailId, String imagePath) throws ServiceException;

    String updateUserDetailStatus(Long id, String status) throws ServiceException;

}
