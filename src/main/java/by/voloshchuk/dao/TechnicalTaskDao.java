package by.voloshchuk.dao;

import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.exception.DaoException;

import java.util.List;

public interface TechnicalTaskDao {

    /**
     * Technical task data to save.
     *
     * @param technicalTask - entity with technical task data for save
     * @return boolean result of adding
     */
    boolean addTechnicalTask(TechnicalTask technicalTask) throws DaoException;

    /**
     * Find technical task by identifier.
     *
     * @param id - id of technical task to find
     * @return {@link TechnicalTask}
     */
    TechnicalTask findTechnicalTaskById(Long id) throws DaoException;

    /**
     * Find all technical task with same status.
     *
     * @param status - required status of technical task
     * @return list of {@link TechnicalTask}
     */
    List<TechnicalTask> findTechnicalTasksByStatus(String status) throws DaoException;

    /**
     * Find technical task associated with user.
     *
     * @param id - id of user with technical tasks
     * @return list of {@link TechnicalTask}
     */
    List<TechnicalTask> findTechnicalTasksByUserId(Long id) throws DaoException;

    /**
     * Technical task data update.
     *
     * @param technicalTask - data for technical task updating
     * @return {@link TechnicalTask}
     */
    TechnicalTask updateTechnicalTask(TechnicalTask technicalTask) throws DaoException;

    /**
     * Technical task data deletion.
     *
     * @param id - technical task id to delete
     * @return boolean result of deletion
     */
    boolean removeTechnicalTask(Long id) throws DaoException;

}
