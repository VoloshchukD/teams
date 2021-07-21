package by.voloshchuk.service.impl;

import by.voloshchuk.dao.UserDao;
import by.voloshchuk.dao.UserDetailDao;
import by.voloshchuk.dao.impl.ConstantColumnName;
import by.voloshchuk.dao.impl.UserDaoImpl;
import by.voloshchuk.dao.impl.UserDetailDaoImpl;
import by.voloshchuk.entity.User;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.UserService;
import by.voloshchuk.servlet.command.CommandAttribute;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    private UserDetailDao userDetailDao = new UserDetailDaoImpl();

    public boolean addUser(User user) throws ServiceException {
        boolean result = false;
        try {
            userDetailDao.addUserDetail(user.getUserDetail());
            String password = user.getPassword();
            String hash = BCrypt.hashpw(password, BCrypt.gensalt());
            user.setPassword(hash);
            result = userDao.addUser(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    public Map<String, Integer> findBasicData() throws ServiceException {
        Map<String, Integer> resultData = new HashMap<>();
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

    public User checkUser(String email, String password) throws ServiceException {
        User resultUser = null;
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

    public void resetPassword(User user) {
//        Password
        user.getPassword();
        String password = "abcd";
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
//        hash
//        BCrypt.checkpw(password, hash)
    }

}
