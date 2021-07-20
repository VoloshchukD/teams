package by.voloshchuk.dao;

import by.voloshchuk.entity.Task;
import by.voloshchuk.exception.DaoException;

import java.util.List;

public interface TaskDao {

    boolean addTask(Task task) throws DaoException;

    Task findTaskById(Long id) throws DaoException;

    List<Task> findTaskByProjectId(Long projectId) throws DaoException;

    List<Task> findTaskByUserIdAndProjectId(Long userId, Long projectId) throws DaoException;

    Task updateTask(Task task) throws DaoException;

    boolean removeTask(Long id) throws DaoException;

}
