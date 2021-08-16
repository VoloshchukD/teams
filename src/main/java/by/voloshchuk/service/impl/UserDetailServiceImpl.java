package by.voloshchuk.service.impl;

import by.voloshchuk.dao.DaoProvider;
import by.voloshchuk.dao.UserDetailDao;
import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.entity.dto.UserDto;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.UserDetailService;
import by.voloshchuk.service.validator.Validator;
import by.voloshchuk.service.validator.ValidatorProvider;
import by.voloshchuk.util.DtoEntityConverter;

public class UserDetailServiceImpl implements UserDetailService {

    private static DaoProvider daoProvider = DaoProvider.getInstance();

    @Override
    public UserDetail findUserDetailByUserId(Long userId) throws ServiceException {
        UserDetail userDetail = null;
        UserDetailDao userDetailDao = daoProvider.getUserDetailDao();
        try {
            userDetail = userDetailDao.findUserDetailByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Exception while find user detail ", e);
        }
        return userDetail;
    }

    @Override
    public UserDetail updateUserDetail(UserDto userDto) throws ServiceException {
        UserDetailDao userDetailDao = daoProvider.getUserDetailDao();
        UserDetail resultUserDetail = null;
        Validator<UserDto> userValidator = ValidatorProvider.getInstance().getUserValidator();
        if (userValidator.validateUpdateData(userDto)) {
            try {
                UserDetail userDetail = DtoEntityConverter.buildUserDetail(userDto);
                userDetail.setId(userDto.getUserDetailId());
                resultUserDetail = userDetailDao.updateUserDetail(userDetail);
            } catch (DaoException e) {
                throw new ServiceException("Exception while update user detail ", e);
            }
        }
        return resultUserDetail;
    }

    @Override
    public String updateUserDetailImage(Long userDetailId, String imagePath) throws ServiceException {
        UserDetailDao userDetailDao = daoProvider.getUserDetailDao();
        String resultImagePath = null;
        try {
            resultImagePath = userDetailDao.updateUserDetailImage(userDetailId, imagePath);
        } catch (DaoException e) {
            throw new ServiceException("Exception while update user detail ", e);
        }
        return resultImagePath;
    }

    @Override
    public String updateUserDetailStatus(Long id, String status) throws ServiceException {
        UserDetailDao userDetailDao = daoProvider.getUserDetailDao();
        String resultStatus = null;
        try {
            resultStatus = userDetailDao.updateUserDetailStatus(id, status);
        } catch (DaoException e) {
            throw new ServiceException("Exception while update user detail ", e);
        }
        return resultStatus;
    }

}
