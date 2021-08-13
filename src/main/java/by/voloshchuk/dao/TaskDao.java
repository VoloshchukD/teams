package by.voloshchuk.dao;

import by.voloshchuk.entity.Task;
import by.voloshchuk.exception.DaoException;

import java.util.List;

public interface TaskDao {

    boolean addTask(Task task) throws DaoException;

    Task findTaskById(Long id) throws DaoException;

    List<Task> findTasksByProjectIdAndUserId(Long projectId, Long userId) throws DaoException;

    List<Task> findTaskByProjectIdAndStatus(Long projectId, String status) throws DaoException;

    Task updateTask(Task task) throws DaoException;

    String updateTaskStatus(Long taskId, String status) throws DaoException;

    Integer updateTaskHours(Long taskId, Integer hours) throws DaoException;

    boolean removeTask(Long id) throws DaoException;

}
