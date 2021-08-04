package by.voloshchuk.service.impl;

import by.voloshchuk.dao.DaoProvider;
import by.voloshchuk.dao.TaskDao;
import by.voloshchuk.dao.TechnicalTaskDao;
import by.voloshchuk.entity.Task;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.TaskService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TaskServiceImpl implements TaskService {

    private static DaoProvider daoProvider = DaoProvider.getInstance();

    @Override
    public boolean addTask(Task task) throws ServiceException {
        boolean result = false;
        TaskDao taskDao = daoProvider.getTaskDao();
        try {
            result = taskDao.addTask(task);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<Task> findTaskByProjectId(Long projectId) throws ServiceException {
        List<Task> tasks = null;
        TaskDao taskDao = daoProvider.getTaskDao();
        try {
            tasks = taskDao.findTaskByProjectId(projectId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return tasks;
    }

    @Override
    public String updateTaskStatus(Long taskId, String status) throws ServiceException {
        String resultStatus = null;
        TaskDao taskDao = daoProvider.getTaskDao();
        try {
            resultStatus = taskDao.updateTaskStatus(taskId, status);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return resultStatus;
    }

    @Override
    public Integer updateTaskHours(Long taskId, Integer hours) throws ServiceException {
        Integer resultHours = null;
        TaskDao taskDao = daoProvider.getTaskDao();
        try {
            resultHours = taskDao.updateTaskHours(taskId, hours);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return resultHours;
    }

}
