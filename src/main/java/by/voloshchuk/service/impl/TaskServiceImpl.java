package by.voloshchuk.service.impl;

import by.voloshchuk.dao.DaoProvider;
import by.voloshchuk.dao.TaskDao;
import by.voloshchuk.entity.Task;
import by.voloshchuk.entity.dto.TaskDto;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.TaskService;
import by.voloshchuk.service.validator.Validator;
import by.voloshchuk.service.validator.ValidatorProvider;
import by.voloshchuk.util.DtoEntityConverter;

import java.util.List;

public class TaskServiceImpl implements TaskService {

    private static DaoProvider daoProvider = DaoProvider.getInstance();

    @Override
    public boolean addTask(TaskDto taskDto) throws ServiceException {
        boolean result = false;
        TaskDao taskDao = daoProvider.getTaskDao();
        Validator<TaskDto> taskValidator = ValidatorProvider.getInstance().getTaskValidator();
        if (taskValidator.validateCreateData(taskDto)) {
            try {
                Task task = DtoEntityConverter.buildTaskEntity(taskDto);
                task.setProjectId(Long.parseLong(taskDto.getProjectId()));
                task.setStatus(Task.TaskStatus.TO_DO);
                result = taskDao.addTask(task);
            } catch (DaoException e) {
                throw new ServiceException("Exception while add task ", e);
            }
        }
        return result;
    }

    @Override
    public List<Task> findTasksByProjectIdAndUserId(Long projectId, Long userId)
            throws ServiceException {
        List<Task> tasks = null;
        TaskDao taskDao = daoProvider.getTaskDao();
        try {
            tasks = taskDao.findTasksByProjectIdAndUserId(projectId, userId);
        } catch (DaoException e) {
            throw new ServiceException("Exception while find task ", e);
        }
        return tasks;
    }

    @Override
    public List<Task> findTaskByProjectIdAndStatus(Long projectId, String status)
            throws ServiceException {
        List<Task> tasks = null;
        TaskDao taskDao = daoProvider.getTaskDao();
        try {
            tasks = taskDao.findTaskByProjectIdAndStatus(projectId, status);
        } catch (DaoException e) {
            throw new ServiceException("Exception while find task ", e);
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
            throw new ServiceException("Exception while update task ", e);
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
            throw new ServiceException("Exception while update task ", e);
        }
        return resultHours;
    }

    @Override
    public Task updateTask(TaskDto taskDto) throws ServiceException {
        Task updatedTask = null;
        TaskDao taskDao = daoProvider.getTaskDao();
        Validator<TaskDto> taskValidator = ValidatorProvider.getInstance().getTaskValidator();
        if (taskValidator.validateUpdateData(taskDto)) {
            try {
                Task task = DtoEntityConverter.buildTaskEntity(taskDto);
                task.setId(taskDto.getTaskId());
                updatedTask = taskDao.updateTask(task);
            } catch (DaoException e) {
                throw new ServiceException("Exception while update task ", e);
            }
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
            throw new ServiceException("Exception while remove task ", e);
        }
        return deleted;
    }

}
