package by.voloshchuk.dao;

import by.voloshchuk.entity.Task;
import by.voloshchuk.exception.DaoException;

import java.util.List;

public interface TaskDao {

    /**
     * Save task data to storage.
     *
     * @param task - required data for saving
     * @return boolean result of save
     */
    boolean addTask(Task task) throws DaoException;

    /**
     * Find tasks on the users project.
     *
     * @param projectId - id of project with tasks
     * @param userId    - id of user on project
     * @return list of {@link Task}
     */
    List<Task> findTasksByProjectIdAndUserId(Long projectId, Long userId) throws DaoException;

    /**
     * Find all tasks on project with same status.
     *
     * @param projectId - id of project with tasks
     * @param status    - status to find
     * @return list of {@link Task}
     */
    List<Task> findTaskByProjectIdAndStatus(Long projectId, String status) throws DaoException;

    /**
     * Task data for update.
     *
     * @param task - data for task updating
     * @return {@link Task}
     */
    Task updateTask(Task task) throws DaoException;

    /**
     * Task status data reset.
     *
     * @param taskId - id of task to update
     * @param status - status to update
     * @return updated status
     */
    String updateTaskStatus(Long taskId, String status) throws DaoException;

    /**
     * Task hours data reset.
     *
     * @param taskId - id of task to update
     * @param hours  - hours value for update
     * @return updated hours
     */
    Integer updateTaskHours(Long taskId, Integer hours) throws DaoException;

    /**
     * Task data to delete.
     *
     * @param id - task id to delete
     * @return boolean result of removing
     */
    boolean removeTask(Long id) throws DaoException;

}
