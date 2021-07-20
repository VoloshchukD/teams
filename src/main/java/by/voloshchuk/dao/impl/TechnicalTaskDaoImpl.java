package by.voloshchuk.dao.impl;

import by.voloshchuk.dao.TechnicalTaskDao;
import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TechnicalTaskDaoImpl implements TechnicalTaskDao {

    private static final String SQL_ADD_TECHNICAL_TASK = "INSERT INTO technical_tasks (overview, " +
            "deadline, workers_amount, status, customer_id) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_FIND_TECHNICAL_TASK_BY_ID = "SELECT * FROM technical_tasks " +
            "WHERE technical_task_id = ?";

    private static final String SQL_FIND_TECHNICAL_TASKS_BY_CUSTOMER_ID = "SELECT * FROM teams.technical_tasks " +
            "WHERE teams.technical_tasks.customer_id = ?";

    private static final String SQL_FIND_TECHNICAL_TASKS_BY_STATUS = "SELECT * FROM teams.technical_tasks " +
            "WHERE teams.technical_tasks.status = ?";

    private static final String SQL_UPDATE_TECHNICAL_TASK = "UPDATE technical_tasks SET overview = ?, " +
            "deadline = ?, workers_amount = ?, status = ? WHERE technical_task_id = ?";

    private static final String SQL_DELETE_TECHNICAL_TASK = "DELETE FROM technical_tasks " +
            "WHERE technical_task_id = ?";

    private CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();

    public boolean addTechnicalTask(TechnicalTask technicalTask) throws DaoException {
        boolean isAdded = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_TECHNICAL_TASK)) {
            statement.setString(1, technicalTask.getOverview());
            statement.setTimestamp(2, new Timestamp(technicalTask.getDeadline().getTime()));
            statement.setString(3, String.valueOf(technicalTask.getWorkersAmount()));
            statement.setString(4, technicalTask.getStatus());
            statement.setString(5, String.valueOf(technicalTask.getCustomer().getId()));
            isAdded = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isAdded;
    }

    public TechnicalTask findTechnicalTaskById(Long id) throws DaoException {
        TechnicalTask technicalTask = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_TECHNICAL_TASK_BY_ID)) {
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();
            technicalTask = new TechnicalTask();
            if (resultSet.next()) {
                technicalTask.setId(Long.valueOf(resultSet.getString(ConstantColumnName.TECHNICAL_TASK_ID)));
                Timestamp timestamp = resultSet.getTimestamp(ConstantColumnName.TECHNICAL_TASK_DEADLINE);
                Date date = new Date(timestamp.getTime());
                technicalTask.setDeadline(date);
                technicalTask.setOverview(resultSet.getString(ConstantColumnName.TECHNICAL_TASK_OVERVIEW));
                technicalTask.setWorkersAmount(Integer.parseInt(resultSet.getString(
                        ConstantColumnName.TECHNICAL_TASK_WORKERS_AMOUNT)));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return technicalTask;
    }

    public List<TechnicalTask> findTechnicalTasksByStatus(String status) throws DaoException {
        List<TechnicalTask> technicalTasks = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_TECHNICAL_TASKS_BY_STATUS)) {
            statement.setString(1, status);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TechnicalTask technicalTask = new TechnicalTask();
                technicalTask.setId(Long.valueOf(resultSet.getString(ConstantColumnName.TECHNICAL_TASK_ID)));
                Timestamp timestamp = resultSet.getTimestamp(ConstantColumnName.TECHNICAL_TASK_DEADLINE);
                Date date = new Date(timestamp.getTime());
                technicalTask.setDeadline(date);
                technicalTask.setOverview(resultSet.getString(ConstantColumnName.TECHNICAL_TASK_OVERVIEW));
                technicalTask.setOverview(resultSet.getString(ConstantColumnName.TECHNICAL_TASK_STATUS));
                technicalTask.setWorkersAmount(Integer.parseInt(resultSet.getString(
                        ConstantColumnName.TECHNICAL_TASK_WORKERS_AMOUNT)));
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
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_TECHNICAL_TASKS_BY_CUSTOMER_ID)) {
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TechnicalTask technicalTask = new TechnicalTask();
                technicalTask.setId(Long.valueOf(resultSet.getString(ConstantColumnName.TECHNICAL_TASK_ID)));
                Timestamp timestamp = resultSet.getTimestamp(ConstantColumnName.TECHNICAL_TASK_DEADLINE);
                Date date = new Date(timestamp.getTime());
                technicalTask.setDeadline(date);
                technicalTask.setOverview(resultSet.getString(ConstantColumnName.TECHNICAL_TASK_OVERVIEW));
                technicalTask.setOverview(resultSet.getString(ConstantColumnName.TECHNICAL_TASK_STATUS));
                technicalTask.setWorkersAmount(Integer.parseInt(resultSet.getString(
                        ConstantColumnName.TECHNICAL_TASK_WORKERS_AMOUNT)));
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
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_TECHNICAL_TASK)) {
            statement.setString(1, technicalTask.getOverview());
            statement.setTimestamp(2, new Timestamp(technicalTask.getDeadline().getTime()));
            statement.setString(3, String.valueOf(technicalTask.getWorkersAmount()));
            statement.setString(4, technicalTask.getStatus());
            statement.setString(5, String.valueOf(technicalTask.getId()));
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
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_TECHNICAL_TASK)) {
            statement.setString(1, String.valueOf(id));
            isRemoved = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isRemoved;
    }

}
