package by.voloshchuk.dao.builder;

import by.voloshchuk.dao.ConstantColumnName;
import by.voloshchuk.entity.Task;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskBuilder implements Builder<Task> {

    @Override
    public Task buildEntity(ResultSet resultSet) throws SQLException {
        Task task = new Task();
        task.setId(resultSet.getLong(ConstantColumnName.TASK_ID));
        task.setName(resultSet.getString(ConstantColumnName.TASK_NAME));
        task.setDetails(resultSet.getString(ConstantColumnName.TASK_DETAILS));
        task.setPlannedTime(resultSet.getInt(ConstantColumnName.TASK_PLANNED_TIME));
        task.setTrackedTime(resultSet.getInt(ConstantColumnName.TASK_TRACKED_TIME));
        task.setStatus(Task.TaskStatus.valueOf(resultSet.getString(ConstantColumnName.TASK_STATUS)));
        return task;
    }

}
