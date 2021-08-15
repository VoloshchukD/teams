package by.voloshchuk.dao.builder;

import by.voloshchuk.dao.ConstantColumnName;
import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.TechnicalTask;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ProjectBuilder implements Builder<Project> {

    @Override
    public Project buildEntity(ResultSet resultSet) throws SQLException {
        Project project = new Project();
        project.setId(resultSet.getLong(ConstantColumnName.PROJECT_ID));
        project.setName(resultSet.getString(ConstantColumnName.PROJECT_NAME));
        project.setDescription(resultSet.getString(ConstantColumnName.PROJECT_DESCRIPTION));
        Timestamp timestamp = resultSet.getTimestamp(ConstantColumnName.PROJECT_START_DATE);
        Date date = new Date(timestamp.getTime());
        project.setStartDate(date);
        project.setState(Project.ProjectStatus.valueOf(
                resultSet.getString(ConstantColumnName.PROJECT_STATE)));
        project.setTechnicalTaskId(resultSet.getLong(ConstantColumnName.PROJECT_TECHNICAL_TASK_ID));
        return project;
    }

}
