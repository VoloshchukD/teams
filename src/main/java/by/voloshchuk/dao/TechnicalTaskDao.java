package by.voloshchuk.dao;

import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.exception.DaoException;

import java.util.List;

public interface TechnicalTaskDao {

    boolean addTechnicalTask(TechnicalTask technicalTask) throws DaoException;

    TechnicalTask findTechnicalTaskById(Long id) throws DaoException;

    List<TechnicalTask> findTechnicalTasksByStatus(String status) throws DaoException;

    List<TechnicalTask> findTechnicalTasksByCustomerId(Long id) throws DaoException;

    TechnicalTask updateTechnicalTask(TechnicalTask technicalTask) throws DaoException;

    boolean removeTechnicalTask(Long id) throws DaoException;

}
