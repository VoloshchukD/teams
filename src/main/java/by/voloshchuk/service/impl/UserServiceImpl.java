package by.voloshchuk.service.impl;

import by.voloshchuk.controller.command.CommandAttribute;
import by.voloshchuk.dao.DaoProvider;
import by.voloshchuk.dao.UserDao;
import by.voloshchuk.dao.UserDetailDao;
import by.voloshchuk.dao.impl.ConstantColumnName;
import by.voloshchuk.dao.impl.UserDaoImpl;
import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.entity.User;
import by.voloshchuk.entity.dto.UserDto;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.UserService;
import by.voloshchuk.service.validator.Validator;
import by.voloshchuk.service.validator.ValidatorProvider;
import by.voloshchuk.util.DtoBuilder;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private static DaoProvider daoProvider = DaoProvider.getInstance();

    public boolean addUser(UserDto userDto) throws ServiceException {
        boolean result = false;
        UserDao userDao = daoProvider.getUserDao();
        UserDetailDao userDetailDao = daoProvider.getUserDetailDao();
        User user = DtoBuilder.buildUser(userDto);
        Validator<UserDto> userValidator = ValidatorProvider.getInstance().getUserValidator();
        if (userValidator.validateCreateData(userDto)) {
            try {
                String password = userDto.getPassword();
                String hash = BCrypt.hashpw(password, BCrypt.gensalt());
                user.setPassword(hash);
                result = userDao.addUser(user);
                userDto.setUserId(user.getId());
                userDto.setUserDetailId(user.getUserDetail().getId());
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public Map<String, Integer> findBasicData() throws ServiceException {
        Map<String, Integer> resultData = new HashMap<>();
        UserDao userDao = new UserDaoImpl();
        try {
            Map<String, Integer> foundedData = userDao.findBasicData();
            resultData.put(CommandAttribute.YEARS_ON_MARKET, foundedData.get(ConstantColumnName.BASIC_DATA_YEARS));
            resultData.put(CommandAttribute.PROJECTS_AMOUNT, foundedData.get(ConstantColumnName.BASIC_DATA_PROJECTS));
            resultData.put(CommandAttribute.PROJECTS_PRODUCTIVITY, foundedData.get(ConstantColumnName.BASIC_DATA_PRODUCTIVITY));
            resultData.put(CommandAttribute.CUSTOMERS_AMOUNT, foundedData.get(ConstantColumnName.BASIC_DATA_CUSTOMERS_AMOUNT));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return resultData;
    }

    @Override
    public List<User> findUsersByProjectId(Long projectId) throws ServiceException {
        List<User> users = null;
        UserDao userDao = daoProvider.getUserDao();
        try {
            users = userDao.findUsersByProjectId(projectId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return users;
    }

    public User checkUser(String email, String password) throws ServiceException {
        User resultUser = null;
        UserDao userDao = new UserDaoImpl();
        try {
            User user = userDao.findUserByEmail(email);
            boolean match = BCrypt.checkpw(password, user.getPassword());
            if (match) {
                resultUser = user;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return resultUser;
    }

    public List<User> findAllByEmployeeRequirement(EmployeeRequirement requirements) throws ServiceException {
        List<User> users = null;
        UserDao userDao = daoProvider.getUserDao();
        try {
            users = userDao.findAllByEmployeeRequirement(requirements);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public List<User> findUsersByPrimarySkill(String primarySkill) throws ServiceException {
        List<User> users = null;
        UserDao userDao = daoProvider.getUserDao();
        try {
            users = userDao.findUsersByPrimarySkill(primarySkill);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return users;
    }

    public boolean addUserToProject(Long userId, Long projectId) throws ServiceException {
        boolean result = false;
        UserDao userDao = daoProvider.getUserDao();
        try {
            result = userDao.addUserToProject(userId, projectId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean removeUserFromProject(Long userId, Long projectId) throws ServiceException {
        boolean result = false;
        UserDao userDao = daoProvider.getUserDao();
        try {
            result = userDao.removeUserFromProject(userId, projectId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

}
