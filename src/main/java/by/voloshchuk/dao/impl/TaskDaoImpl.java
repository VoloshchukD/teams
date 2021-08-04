package by.voloshchuk.dao.impl;

import by.voloshchuk.dao.TaskDao;
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

    private static final String ADD_TASK_QUERY = "INSERT INTO tasks (name, " +
            "details, hours, status, project_id, developer_id) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String FIND_TASK_BY_ID_QUERY = "SELECT * FROM tasks WHERE task_id = ?";

    private static final String FIND_TASKS_BY_PROJECT_ID_QUERY = "SELECT * FROM tasks " +
            "INNER JOIN users ON tasks.developer_id = users.user_id " +
            "INNER JOIN user_details ON users.user_id = user_details.user_detail_id WHERE project_id = ?";

    private static final String FIND_TASKS_BY_PROJECT_ID_QUERY_AND_STATUS = "SELECT * FROM tasks " +
            "INNER JOIN users ON tasks.developer_id = users.user_id " +
            "INNER JOIN user_details ON users.user_id = user_details.user_detail_id " +
            "WHERE tasks.project_id = ? AND tasks.status = ?";

    private static final String FIND_TASKS_BY_USER_ID_AND_PROJECT_ID_QUERY = "SELECT * FROM tasks WHERE project_id = ? " +
            "AND developer_id = ?";

    private static final String UPDATE_TASK_QUERY = "UPDATE tasks SET name = ?, details = ?," +
            " hours = ?, status = ?, developer_id = ? WHERE task_id = ?";

    private static final String UPDATE_TASK_STATUS_QUERY = "UPDATE tasks SET status = ? WHERE task_id = ?";

    private static final String UPDATE_TASK_HOURS_QUERY = "UPDATE tasks SET hours = ? WHERE task_id = ?";

    private static final String DELETE_TASK_QUERY = "DELETE FROM tasks WHERE task_id = ?";

    private CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();

    public boolean addTask(Task task) throws DaoException {
        boolean isAdded = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_TASK_QUERY)) {
            statement.setString(1, task.getName());
            statement.setString(2, task.getDetails());
            statement.setInt(3, task.getHours());
            statement.setString(4, task.getStatus().toString());
            statement.setLong(5, task.getProject().getId());
            statement.setLong(6, task.getDeveloper().getId());
            isAdded = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isAdded;
    }

    public Task findTaskById(Long id) throws DaoException {
        Task task = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_TASK_BY_ID_QUERY)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            task = new Task();
            if (resultSet.next()) {
                task.setId(resultSet.getLong(ConstantColumnName.TASK_ID));
                task.setName(resultSet.getString(ConstantColumnName.TASK_NAME));
                task.setDetails(resultSet.getString(ConstantColumnName.TASK_DETAILS));
                task.setHours(resultSet.getInt(ConstantColumnName.TASK_HOURS));
                task.setStatus(Task.TaskStatus.valueOf(resultSet.getString(ConstantColumnName.TASK_STATUS)));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return task;
    }

    public List<Task> findTaskByProjectId(Long projectId) throws DaoException {
        List<Task> tasks = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_TASKS_BY_PROJECT_ID_QUERY)) {
            statement.setLong(1, projectId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getLong(ConstantColumnName.TASK_ID));
                task.setName(resultSet.getString(ConstantColumnName.TASK_NAME));
                task.setDetails(resultSet.getString(ConstantColumnName.TASK_DETAILS));
                task.setHours(resultSet.getInt(ConstantColumnName.TASK_HOURS));
                task.setStatus(Task.TaskStatus.valueOf(resultSet.getString(ConstantColumnName.TASK_STATUS)));
                User user = new User();
                UserDetail userDetail = new UserDetail();
                userDetail.setImagePath(resultSet.getString(ConstantColumnName.USER_DETAIL_IMAGE));
                userDetail.setFirstName(resultSet.getString(ConstantColumnName.USER_DETAIL_FIRST_NAME));
                userDetail.setLastName(resultSet.getString(ConstantColumnName.USER_DETAIL_LAST_NAME));
                user.setUserDetail(userDetail);
                task.setDeveloper(user);
                tasks.add(task);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return tasks;
    }

    public List<Task> findTaskByProjectIdAndStatus(Long projectId, String status) throws DaoException {
        List<Task> tasks = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     FIND_TASKS_BY_PROJECT_ID_QUERY_AND_STATUS)) {
            statement.setLong(1, projectId);
            statement.setString(2, status);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getLong(ConstantColumnName.TASK_ID));
                task.setName(resultSet.getString(ConstantColumnName.TASK_NAME));
                task.setDetails(resultSet.getString(ConstantColumnName.TASK_DETAILS));
                task.setHours(resultSet.getInt(ConstantColumnName.TASK_HOURS));
                task.setStatus(Task.TaskStatus.valueOf(resultSet.getString(ConstantColumnName.TASK_STATUS)));
                User user = new User();
                UserDetail userDetail = new UserDetail();
                userDetail.setSalary(resultSet.getInt(ConstantColumnName.USER_DETAIL_SALARY));
                user.setUserDetail(userDetail);
                task.setDeveloper(user);
                tasks.add(task);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return tasks;
    }

    public List<Task> findTaskByUserIdAndProjectId(Long userId, Long projectId) throws DaoException {
        List<Task> tasks = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     FIND_TASKS_BY_USER_ID_AND_PROJECT_ID_QUERY)) {
            statement.setLong(1, userId);
            statement.setLong(2, projectId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getLong(ConstantColumnName.TASK_ID));
                task.setName(resultSet.getString(ConstantColumnName.TASK_NAME));
                task.setDetails(resultSet.getString(ConstantColumnName.TASK_DETAILS));
                task.setHours(resultSet.getInt(ConstantColumnName.TASK_HOURS));
                task.setStatus(
                        Task.TaskStatus.valueOf(resultSet.getString(ConstantColumnName.TASK_STATUS)));
                tasks.add(task);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return tasks;
    }

    public Task updateTask(Task task) throws DaoException {
        Task resultTask = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TASK_QUERY)) {
            statement.setString(1, task.getName());
            statement.setString(2, task.getDetails());
            statement.setInt(3, task.getHours());
            statement.setString(4, task.getStatus().toString());
            statement.setLong(5, task.getDeveloper().getId());
            statement.setLong(6, task.getId());

            int result = statement.executeUpdate();
            if (result == 1) {
                resultTask = task;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return resultTask;
    }

    public String updateTaskStatus(Long taskId, String status) throws DaoException {
        String resultStatus = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TASK_STATUS_QUERY)) {
            statement.setString(1, status);
            statement.setLong(2, taskId);
            int result = statement.executeUpdate();
            if (result == 1) {
                resultStatus = status;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return resultStatus;
    }

    public Integer updateTaskHours(Long taskId, Integer hours) throws DaoException {
        Integer resultHours = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TASK_HOURS_QUERY)) {
            statement.setInt(1, hours);
            statement.setLong(2, taskId);
            int result = statement.executeUpdate();
            if (result == 1) {
                resultHours = hours;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return resultHours;
    }

    public boolean removeTask(Long id) throws DaoException {
        boolean isRemoved = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TASK_QUERY)) {
            statement.setLong(1, id);
            isRemoved = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isRemoved;
    }

}
