package by.voloshchuk.service;

import by.voloshchuk.entity.Task;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;

import java.util.List;

public interface TaskService {

    boolean addTask(Task task) throws ServiceException;

    List<Task> findTaskByProjectId(Long projectId) throws ServiceException;

    List<Task> findTaskByProjectIdAndStatus(Long projectId, String status) throws ServiceException;

    String updateTaskStatus(Long taskId, String status) throws ServiceException;

    Integer updateTaskHours(Long taskId, Integer hours) throws ServiceException;

}
