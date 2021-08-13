package by.voloshchuk.service.impl;

import by.voloshchuk.dao.DaoProvider;
import by.voloshchuk.dao.TaskDao;
import by.voloshchuk.dao.TechnicalTaskDao;
import by.voloshchuk.entity.Task;
import by.voloshchuk.entity.TechnicalTask;
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
    public List<Task> findTasksByProjectIdAndUserId(Long projectId, Long userId) throws ServiceException {
        List<Task> tasks = null;
        TaskDao taskDao = daoProvider.getTaskDao();
        try {
            tasks = taskDao.findTasksByProjectIdAndUserId(projectId, userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return tasks;
    }

    @Override
    public List<Task> findTaskByProjectIdAndStatus(Long projectId, String status) throws ServiceException {
        List<Task> tasks = null;
        TaskDao taskDao = daoProvider.getTaskDao();
        try {
            tasks = taskDao.findTaskByProjectIdAndStatus(projectId, status);
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

    @Override
    public Task updateTask(Task task) throws ServiceException {
        Task updatedTask = null;
        TaskDao taskDao = daoProvider.getTaskDao();
        try {
            updatedTask = taskDao.updateTask(task);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return updatedTask;
    }

    @Override
    public boolean removeTask(Long id) throws ServiceException {
        boolean deleted = false;
        TaskDao taskDao = daoProvider.getTaskDao();
        try {
            deleted = taskDao.removeTask(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return deleted;
    }

}
