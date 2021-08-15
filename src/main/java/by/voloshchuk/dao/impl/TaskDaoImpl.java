package by.voloshchuk.dao.impl;

import by.voloshchuk.dao.ConstantColumnName;
import by.voloshchuk.dao.ConstantDaoQuery;
import by.voloshchuk.dao.DaoExecutor;
import by.voloshchuk.dao.TaskDao;
import by.voloshchuk.dao.builder.Builder;
import by.voloshchuk.dao.builder.TaskBuilder;
import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.Task;
import by.voloshchuk.entity.User;
import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoImpl implements TaskDao {

    private final DaoExecutor<Task> executor;

    private final Builder<Task> builder;

    public TaskDaoImpl() {
        builder = new TaskBuilder();
        executor = new DaoExecutor<>(builder);
    }

    public boolean addTask(Task task) throws DaoException {
        Object[] parameters = {task.getName(), task.getDetails(), task.getPlannedTime(),
                task.getStatus().toString(), task.getProjectId(),
                task.getDeveloper().getId()};
        boolean added = executor.executeUpdate(ConstantDaoQuery.ADD_TASK_QUERY, parameters);
        return added;
    }

    public List<Task> findTasksByProjectIdAndUserId(Long projectId, Long userId)
            throws DaoException {
        List<Task> tasks = new ArrayList<>();
        CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantDaoQuery.FIND_TASKS_BY_PROJECT_ID_AND_USER_ID_QUERY)) {
            Object[] parameters = {projectId, userId};
            DaoExecutor.fillStatement(statement, parameters);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Task task = builder.buildEntity(resultSet);
                User user = new User();
                UserDetail userDetail = new UserDetail();
                userDetail.setImagePath(resultSet.getString(
                        ConstantColumnName.USER_DETAIL_IMAGE));
                userDetail.setFirstName(resultSet.getString(
                        ConstantColumnName.USER_DETAIL_FIRST_NAME));
                userDetail.setLastName(resultSet.getString(
                        ConstantColumnName.USER_DETAIL_LAST_NAME));
                user.setUserDetail(userDetail);
                task.setDeveloper(user);
                tasks.add(task);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return tasks;
    }

    public List<Task> findTaskByProjectIdAndStatus(Long projectId, String status)
            throws DaoException {
        List<Task> tasks = new ArrayList<>();
        CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     ConstantDaoQuery.FIND_TASKS_BY_PROJECT_ID_QUERY_AND_STATUS)) {
            Object[] parameters = {projectId, status};
            DaoExecutor.fillStatement(statement, parameters);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Task task = builder.buildEntity(resultSet);
                User user = new User();
                UserDetail userDetail = new UserDetail();
                userDetail.setSalary(
                        resultSet.getInt(ConstantColumnName.USER_DETAIL_SALARY));
                user.setUserDetail(userDetail);
                task.setDeveloper(user);
                tasks.add(task);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return tasks;
    }

    public Task updateTask(Task task) throws DaoException {
        Task resultTask = null;
        Object[] parameters = {task.getName(), task.getDetails(), task.getPlannedTime(),
                task.getDeveloper().getId(), task.getId()};
        boolean result = executor.executeUpdate(ConstantDaoQuery.UPDATE_TASK_QUERY, parameters);
        if (result) {
            resultTask = task;
        }
        return resultTask;
    }

    public String updateTaskStatus(Long taskId, String status) throws DaoException {
        String resultStatus = null;
        Object[] parameters = {status, taskId};
        boolean result = executor.executeUpdate(
                ConstantDaoQuery.UPDATE_TASK_STATUS_QUERY, parameters);
        if (result) {
            resultStatus = status;
        }
        return resultStatus;
    }

    public Integer updateTaskHours(Long taskId, Integer hours) throws DaoException {
        Integer resultHours = null;
        Object[] parameters = {hours, taskId};
        boolean result = executor.executeUpdate(
                ConstantDaoQuery.UPDATE_TASK_HOURS_QUERY, parameters);
        if (result) {
            resultHours = hours;
        }
        return resultHours;
    }

    public boolean removeTask(Long id) throws DaoException {
        Object[] parameters = {id};
        boolean removed = executor.executeUpdate(
                ConstantDaoQuery.DELETE_TASK_QUERY, parameters);
        return removed;
    }

}
