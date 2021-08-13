package by.voloshchuk.service;

import by.voloshchuk.entity.Task;
import by.voloshchuk.exception.ServiceException;

import java.util.List;

public interface TaskService {

    /**
     * Task adding logics.
     *
     * @param task - entity with required data for adding
     * @return boolean result of adding
     */
    boolean addTask(Task task) throws ServiceException;

    /**
     * Project tasks finding logics.
     *
     * @param projectId - id of project with tasks
     * @param userId - id of user on project
     * @return list of {@link Task}
     */
    List<Task> findTasksByProjectIdAndUserId(Long projectId, Long userId) throws ServiceException;

    /**
     * Project tasks finding logics by status.
     *
     * @param projectId - id of project with tasks
     * @param status    - status of task to be found
     * @return list of {@link Task}
     */
    List<Task> findTaskByProjectIdAndStatus(Long projectId, String status) throws ServiceException;

    /**
     * Task status updating logics.
     *
     * @param taskId - id of task to update
     * @param status - status to update
     * @return updated status
     */
    String updateTaskStatus(Long taskId, String status) throws ServiceException;

    /**
     * Task hours updating logics.
     *
     * @param taskId - id of task to update
     * @param hours  - hours value to update
     * @return updated hours
     */
    Integer updateTaskHours(Long taskId, Integer hours) throws ServiceException;

    /**
     * Task data updating logics.
     *
     * @param task - data for task updating
     * @return {@link Task}
     */
    Task updateTask(Task task) throws ServiceException;

    /**
     * Task removing logics.
     *
     * @param id - task id to delete
     * @return boolean result of removing
     */
    boolean removeTask(Long id) throws ServiceException;

}
