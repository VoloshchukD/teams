package by.voloshchuk.dao.impl;

import by.voloshchuk.dao.ConstantDaoQuery;
import by.voloshchuk.dao.DaoExecutor;
import by.voloshchuk.dao.TechnicalTaskDao;
import by.voloshchuk.dao.builder.Builder;
import by.voloshchuk.dao.builder.TechnicalTaskBuilder;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.exception.DaoException;

import java.sql.Timestamp;
import java.util.List;

public class TechnicalTaskDaoImpl implements TechnicalTaskDao {

    private final DaoExecutor<TechnicalTask> executor;

    public TechnicalTaskDaoImpl() {
        Builder<TechnicalTask> builder = new TechnicalTaskBuilder();
        executor = new DaoExecutor<>(builder);
    }

    @Override
    public boolean addTechnicalTask(TechnicalTask technicalTask) throws DaoException {
        Object[] parameters = {technicalTask.getName(), technicalTask.getOverview(),
                new Timestamp(technicalTask.getDeadline().getTime()),
                technicalTask.getStatus().toString(), technicalTask.getCustomerId()};
        boolean added = executor.executeUpdate(
                ConstantDaoQuery.ADD_TECHNICAL_TASK_QUERY, parameters);
        return added;
    }

    @Override
    public TechnicalTask findTechnicalTaskById(Long id) throws DaoException {
        Object[] parameters = {id};
        TechnicalTask technicalTask = executor.executeQuery(
                ConstantDaoQuery.FIND_TECHNICAL_TASK_BY_ID_QUERY, parameters);
        return technicalTask;
    }

    @Override
    public List<TechnicalTask> findTechnicalTasksByStatus(String status) throws DaoException {
        Object[] parameters = {status};
        List<TechnicalTask> technicalTasks = executor.executeQueryMultipleResult(
                ConstantDaoQuery.FIND_TECHNICAL_TASKS_BY_STATUS_QUERY, parameters);
        return technicalTasks;
    }

    @Override
    public List<TechnicalTask> findTechnicalTasksByUserId(Long id) throws DaoException {
        Object[] parameters = {id};
        List<TechnicalTask> technicalTasks = executor.executeQueryMultipleResult(
                ConstantDaoQuery.FIND_TECHNICAL_TASKS_BY_CUSTOMER_ID_QUERY, parameters);
        return technicalTasks;
    }

    @Override
    public TechnicalTask updateTechnicalTask(TechnicalTask technicalTask) throws DaoException {
        TechnicalTask resultTechnicalTask = null;
        Object[] parameters = {technicalTask.getName(), technicalTask.getOverview(),
                technicalTask.getId()};
        boolean result = executor.executeUpdate(
                ConstantDaoQuery.UPDATE_TECHNICAL_TASK_QUERY, parameters);
        if (result) {
            resultTechnicalTask = technicalTask;
        }
        return resultTechnicalTask;
    }

    @Override
    public boolean removeTechnicalTask(Long id) throws DaoException {
        String[] queries = {ConstantDaoQuery.DELETE_PROJECT_REQUIREMENTS_QUERY,
                ConstantDaoQuery.DELETE_TECHNICAL_TASK_QUERY};
        Object[][] parameters = {{id}, {id}};
        boolean removed = executor.executeUpdateTransactionMultiple(queries, parameters);
        return removed;
    }

}
