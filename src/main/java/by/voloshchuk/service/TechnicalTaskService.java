package by.voloshchuk.service;

import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.entity.dto.TechnicalTaskDto;
import by.voloshchuk.exception.ServiceException;

import java.util.List;

public interface TechnicalTaskService {

    /**
     * Technical task adding logics.
     *
     * @param technicalTaskDto - entity with technical task data for adding
     * @return boolean result of adding
     */
    boolean addTechnicalTask(TechnicalTaskDto technicalTaskDto) throws ServiceException;

    /**
     * Technical task finding logics.
     *
     * @param technicalTaskId - id of technical task to find
     * @return {@link TechnicalTask}
     */
    TechnicalTask findTechnicalTaskById(Long technicalTaskId) throws ServiceException;

    /**
     * Users technical task finding logics.
     *
     * @param userId - id of user with technical tasks
     * @return list of {@link TechnicalTask}
     */
    List<TechnicalTask> findTechnicalTasksByUserId(Long userId) throws ServiceException;

    /**
     * Technical task finding logics by status.
     *
     * @param status - required status of technical task
     * @return list of {@link TechnicalTask}
     */
    List<TechnicalTask> findTechnicalTasksByStatus(String status) throws ServiceException;

    /**
     * Technical task data updating logics.
     *
     * @param technicalTask - data for technical task updating
     * @return {@link TechnicalTask}
     */
    TechnicalTask updateTechnicalTask(TechnicalTask technicalTask) throws ServiceException;

    /**
     * Technical task removing logics.
     *
     * @param id - technical task id to delete
     * @return boolean result of removing
     */
    boolean removeTechnicalTask(Long id) throws ServiceException;

}
