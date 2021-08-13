package by.voloshchuk.dao.impl;

import by.voloshchuk.dao.DaoExecutor;
import by.voloshchuk.dao.TechnicalTaskDao;
import by.voloshchuk.dao.builder.Builder;
import by.voloshchuk.dao.builder.TechnicalTaskBuilder;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.exception.DaoException;

import java.sql.Timestamp;
import java.util.List;

public class TechnicalTaskDaoImpl implements TechnicalTaskDao {

    private static final String ADD_TECHNICAL_TASK_QUERY = "INSERT INTO technical_tasks (name, overview, " +
            "deadline, status, customer_id) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String FIND_TECHNICAL_TASK_BY_ID_QUERY = "SELECT * FROM technical_tasks " +
            "WHERE technical_task_id = ?";

    private static final String FIND_TECHNICAL_TASKS_BY_CUSTOMER_ID_QUERY = "SELECT * FROM technical_tasks " +
            "WHERE technical_tasks.customer_id = ?";

    private static final String FIND_TECHNICAL_TASKS_BY_STATUS_QUERY = "SELECT * FROM technical_tasks " +
            "WHERE technical_tasks.status = ?";

    private static final String UPDATE_TECHNICAL_TASK_QUERY = "UPDATE technical_tasks SET name = ?, overview = ? " +
            "WHERE technical_task_id = ?";

    private static final String DELETE_TECHNICAL_TASK_QUERY = "DELETE FROM technical_tasks " +
            "WHERE technical_task_id = ?";

    private final DaoExecutor<TechnicalTask> executor;

    public TechnicalTaskDaoImpl() {
        Builder<TechnicalTask> builder = new TechnicalTaskBuilder();
        executor = new DaoExecutor<>(builder);
    }

    public boolean addTechnicalTask(TechnicalTask technicalTask) throws DaoException {
        Object[] parameters = {technicalTask.getName(), technicalTask.getOverview(),
                new Timestamp(technicalTask.getDeadline().getTime()),
                technicalTask.getStatus().toString(), technicalTask.getCustomer().getId()};
        boolean added = executor.executeUpdate(ADD_TECHNICAL_TASK_QUERY, parameters);
        return added;
    }

    public TechnicalTask findTechnicalTaskById(Long id) throws DaoException {
        Object[] parameters = {id};
        TechnicalTask technicalTask = executor.executeQuery(FIND_TECHNICAL_TASK_BY_ID_QUERY, parameters);
        return technicalTask;
    }

    public List<TechnicalTask> findTechnicalTasksByStatus(String status) throws DaoException {
        Object[] parameters = {status};
        List<TechnicalTask> technicalTasks = executor.executeQueryMultipleResult(
                FIND_TECHNICAL_TASKS_BY_STATUS_QUERY, parameters);
        return technicalTasks;
    }

    public List<TechnicalTask> findTechnicalTasksByCustomerId(Long id) throws DaoException {
        Object[] parameters = {id};
        List<TechnicalTask> technicalTasks = executor.executeQueryMultipleResult(
                FIND_TECHNICAL_TASKS_BY_CUSTOMER_ID_QUERY, parameters);
        return technicalTasks;
    }

    public TechnicalTask updateTechnicalTask(TechnicalTask technicalTask) throws DaoException {
        TechnicalTask resultTechnicalTask = null;
        Object[] parameters = {technicalTask.getName(), technicalTask.getOverview(), technicalTask.getId()};
        boolean result = executor.executeUpdate(UPDATE_TECHNICAL_TASK_QUERY, parameters);
        if (result) {
            resultTechnicalTask = technicalTask;
        }
        return resultTechnicalTask;
    }

    public boolean removeTechnicalTask(Long id) throws DaoException {
        Object[] parameters = {id};
        boolean removed = executor.executeUpdate(DELETE_TECHNICAL_TASK_QUERY, parameters);
        return removed;
    }

}
