package by.voloshchuk.service.impl;

import by.voloshchuk.dao.DaoProvider;
import by.voloshchuk.dao.TechnicalTaskDao;
import by.voloshchuk.dao.UserDao;
import by.voloshchuk.dao.UserDetailDao;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.entity.User;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.TechnicalTaskService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class TechnicalTaskServiceImpl implements TechnicalTaskService {

    private static DaoProvider daoProvider = DaoProvider.getInstance();

    @Override
    public boolean addTechnicalTask(TechnicalTask technicalTask) throws ServiceException {
        boolean result = false;
        TechnicalTaskDao technicalTaskDao = daoProvider.getTechnicalTaskDao();
        try {
            result = technicalTaskDao.addTechnicalTask(technicalTask);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<TechnicalTask> findTechnicalTasksByUserId(Long useId) throws ServiceException {
        List<TechnicalTask> technicalTasks = null;
        TechnicalTaskDao technicalTaskDao = daoProvider.getTechnicalTaskDao();
        try {
            technicalTasks = technicalTaskDao.findTechnicalTasksByCustomerId(useId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return technicalTasks;
    }

    @Override
    public TechnicalTask findTechnicalTaskById(Long technicalTaskId) throws ServiceException {
        TechnicalTask technicalTask = null;
        TechnicalTaskDao technicalTaskDao = daoProvider.getTechnicalTaskDao();
        try {
            technicalTask = technicalTaskDao.findTechnicalTaskById(technicalTaskId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return technicalTask;
    }

    @Override
    public List<TechnicalTask> findTechnicalTasksByStatus(String status) throws ServiceException {
        List<TechnicalTask> technicalTasks = null;
        TechnicalTaskDao technicalTaskDao = daoProvider.getTechnicalTaskDao();
        try {
            technicalTasks = technicalTaskDao.findTechnicalTasksByStatus(status);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return technicalTasks;
    }

}
