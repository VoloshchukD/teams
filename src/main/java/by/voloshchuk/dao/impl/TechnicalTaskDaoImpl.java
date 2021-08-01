package by.voloshchuk.dao.impl;

import by.voloshchuk.dao.TechnicalTaskDao;
import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TechnicalTaskDaoImpl implements TechnicalTaskDao {

    private static final String ADD_TECHNICAL_TASK_QUERY = "INSERT INTO technical_tasks (name, overview, " +
            "deadline, status, customer_id) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String FIND_TECHNICAL_TASK_BY_ID_QUERY = "SELECT * FROM technical_tasks " +
            "WHERE technical_task_id = ?";

    private static final String FIND_TECHNICAL_TASKS_BY_CUSTOMER_ID_QUERY = "SELECT * FROM teams.technical_tasks " +
            "WHERE teams.technical_tasks.customer_id = ?";

    private static final String FIND_TECHNICAL_TASKS_BY_STATUS_QUERY = "SELECT * FROM teams.technical_tasks " +
            "WHERE teams.technical_tasks.status = ?";

    private static final String UPDATE_TECHNICAL_TASK_QUERY = "UPDATE technical_tasks SET overview = ?, " +
            "deadline = ?, workers_amount = ?, status = ? WHERE technical_task_id = ?";

    private static final String DELETE_TECHNICAL_TASK_QUERY = "DELETE FROM technical_tasks " +
            "WHERE technical_task_id = ?";

    private CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();

    public boolean addTechnicalTask(TechnicalTask technicalTask) throws DaoException {
        boolean isAdded = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_TECHNICAL_TASK_QUERY)) {
            statement.setString(1, technicalTask.getName());
            statement.setString(2, technicalTask.getOverview());
            statement.setTimestamp(3, new Timestamp(technicalTask.getDeadline().getTime()));
            statement.setString(4, technicalTask.getStatus());
            statement.setLong(5, technicalTask.getCustomer().getId());
            isAdded = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isAdded;
    }

    public TechnicalTask findTechnicalTaskById(Long id) throws DaoException {
        TechnicalTask technicalTask = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_TECHNICAL_TASK_BY_ID_QUERY)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            technicalTask = new TechnicalTask();
            if (resultSet.next()) {
                technicalTask.setId(resultSet.getLong(ConstantColumnName.TECHNICAL_TASK_ID));
                technicalTask.setName(resultSet.getString(ConstantColumnName.TECHNICAL_TASK_NAME));
                Timestamp timestamp = resultSet.getTimestamp(ConstantColumnName.TECHNICAL_TASK_DEADLINE);
                Date date = new Date(timestamp.getTime());
                technicalTask.setDeadline(date);
                technicalTask.setOverview(resultSet.getString(ConstantColumnName.TECHNICAL_TASK_OVERVIEW));
                technicalTask.setWorkersAmount(resultSet.getInt(ConstantColumnName.TECHNICAL_TASK_WORKERS_AMOUNT));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return technicalTask;
    }

    public List<TechnicalTask> findTechnicalTasksByStatus(String status) throws DaoException {
        List<TechnicalTask> technicalTasks = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_TECHNICAL_TASKS_BY_STATUS_QUERY)) {
            statement.setString(1, status);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TechnicalTask technicalTask = new TechnicalTask();
                technicalTask.setId(resultSet.getLong(ConstantColumnName.TECHNICAL_TASK_ID));
                technicalTask.setName(resultSet.getString(ConstantColumnName.TECHNICAL_TASK_NAME));
                Timestamp timestamp = resultSet.getTimestamp(ConstantColumnName.TECHNICAL_TASK_DEADLINE);
                Date date = new Date(timestamp.getTime());
                technicalTask.setDeadline(date);
                technicalTask.setOverview(resultSet.getString(ConstantColumnName.TECHNICAL_TASK_OVERVIEW));
                technicalTask.setStatus(resultSet.getString(ConstantColumnName.TECHNICAL_TASK_STATUS));
                technicalTask.setWorkersAmount(resultSet.getInt(ConstantColumnName.TECHNICAL_TASK_WORKERS_AMOUNT));
                technicalTasks.add(technicalTask);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return technicalTasks;
    }

    public List<TechnicalTask> findTechnicalTasksByCustomerId(Long id) throws DaoException {
        List<TechnicalTask> technicalTasks = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_TECHNICAL_TASKS_BY_CUSTOMER_ID_QUERY)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TechnicalTask technicalTask = new TechnicalTask();
                technicalTask.setId(resultSet.getLong(ConstantColumnName.TECHNICAL_TASK_ID));
                technicalTask.setName(resultSet.getString(ConstantColumnName.TECHNICAL_TASK_NAME));
                Timestamp timestamp = resultSet.getTimestamp(ConstantColumnName.TECHNICAL_TASK_DEADLINE);
                Date date = new Date(timestamp.getTime());
                technicalTask.setDeadline(date);
                technicalTask.setOverview(resultSet.getString(ConstantColumnName.TECHNICAL_TASK_OVERVIEW));
                technicalTask.setStatus(resultSet.getString(ConstantColumnName.TECHNICAL_TASK_STATUS));
                technicalTask.setWorkersAmount(resultSet.getInt(ConstantColumnName.TECHNICAL_TASK_WORKERS_AMOUNT));
                technicalTasks.add(technicalTask);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return technicalTasks;
    }

    public TechnicalTask updateTechnicalTask(TechnicalTask technicalTask) throws DaoException {
        TechnicalTask resultTechnicalTask = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TECHNICAL_TASK_QUERY)) {
            statement.setString(1, technicalTask.getOverview());
            statement.setTimestamp(2, new Timestamp(technicalTask.getDeadline().getTime()));
            statement.setInt(3, technicalTask.getWorkersAmount());
            statement.setString(4, technicalTask.getStatus());
            statement.setLong(5, technicalTask.getId());
            int result = statement.executeUpdate();
            if (result == 1) {
                resultTechnicalTask = technicalTask;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return resultTechnicalTask;
    }

    public boolean removeTechnicalTask(Long id) throws DaoException {
        boolean isRemoved = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TECHNICAL_TASK_QUERY)) {
            statement.setLong(1, id);
            isRemoved = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isRemoved;
    }

}
