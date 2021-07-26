package by.voloshchuk.service;

import by.voloshchuk.entity.User;
import by.voloshchuk.exception.ServiceException;

import java.util.Map;

public interface UserService {

    boolean addUser(User user) throws ServiceException;

    User checkUser(String email, String password) throws ServiceException;

    Map<String, Integer> findBasicData() throws ServiceException;

}
