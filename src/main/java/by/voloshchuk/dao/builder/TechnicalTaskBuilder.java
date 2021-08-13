package by.voloshchuk.dao.builder;

import by.voloshchuk.dao.impl.ConstantColumnName;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.entity.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TechnicalTaskBuilder implements Builder<TechnicalTask> {

    @Override
    public TechnicalTask buildEntity(ResultSet resultSet) throws SQLException {
        TechnicalTask technicalTask = new TechnicalTask();
        technicalTask.setId(resultSet.getLong(ConstantColumnName.TECHNICAL_TASK_ID));
        technicalTask.setName(resultSet.getString(ConstantColumnName.TECHNICAL_TASK_NAME));
        Timestamp timestamp = resultSet.getTimestamp(ConstantColumnName.TECHNICAL_TASK_DEADLINE);
        Date date = new Date(timestamp.getTime());
        technicalTask.setDeadline(date);
        technicalTask.setOverview(resultSet.getString(ConstantColumnName.TECHNICAL_TASK_OVERVIEW));
        technicalTask.setStatus(
                TechnicalTask.TechnicalTaskStatus.valueOf(
                        resultSet.getString(ConstantColumnName.TECHNICAL_TASK_STATUS)));
        User user = new User();
        user.setId(resultSet.getLong(ConstantColumnName.TECHNICAL_TASK_CUSTOMER_ID));
        technicalTask.setCustomer(user);
        return technicalTask;
    }

}
