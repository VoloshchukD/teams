package by.voloshchuk.dao.impl;

import by.voloshchuk.dao.ConstantDaoQuery;
import by.voloshchuk.dao.DaoExecutor;
import by.voloshchuk.dao.ProjectDao;
import by.voloshchuk.dao.builder.Builder;
import by.voloshchuk.dao.builder.ProjectBuilder;
import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.entity.dto.ProjectDto;
import by.voloshchuk.exception.DaoException;

import java.sql.*;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {

    private final DaoExecutor<Project> executor;

    public ProjectDaoImpl() {
        Builder<Project> builder = new ProjectBuilder();
        executor = new DaoExecutor<>(builder);
    }

    public boolean addProject(ProjectDto projectDto) throws DaoException {
        boolean added = false;
        CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                ConstantDaoQuery.ADD_PROJECT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            Project project = projectDto.getProject();
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setTimestamp(3, new Timestamp(project.getStartDate().getTime()));
            statement.setString(4, project.getState().toString());
            statement.setLong(5, project.getTechnicalTaskId());
            added = (statement.executeUpdate() == 1);
            if (added) {
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                long projectId = resultSet.getLong(1);
                project.setId(projectId);
                try (PreparedStatement userStatement = connection.prepareStatement(
                        ConstantDaoQuery.ADD_USER_TO_PROJECT_QUERY)) {
                    userStatement.setLong(1, projectId);
                    userStatement.setLong(2, projectDto.getCustomerId());
                    userStatement.executeUpdate();
                    userStatement.setLong(1, projectId);
                    userStatement.setLong(2, projectDto.getManagerId());
                    userStatement.executeUpdate();
                }
                try (PreparedStatement userStatement = connection.prepareStatement(
                        ConstantDaoQuery.UPDATE_TECHNICAL_TASK_STATUS_QUERY)) {
                    userStatement.setString(1,
                            TechnicalTask.TechnicalTaskStatus.ON_PROJECT.toString());
                    userStatement.setLong(2, project.getTechnicalTaskId());
                    userStatement.executeUpdate();
                }
                connection.commit();
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                throw new DaoException(exception);
            }
            throw new DaoException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException exception) {
                throw new DaoException(exception);
            }
        }
        return added;
    }

    public List<Project> findProjectsByUserIdAndState(Long userId, String state)
            throws DaoException {
        Object[] parameters = {userId, state};
        List<Project> projects = executor.executeQueryMultipleResult(
                ConstantDaoQuery.FIND_PROJECTS_BY_USER_ID_AND_STATE_QUERY, parameters);
        return projects;
    }

    public Project updateProject(Project project) throws DaoException {
        Project resultProject = null;
        Object[] parameters = {project.getName(), project.getDescription(),
                project.getId()};
        boolean result = executor.executeUpdate(
                ConstantDaoQuery.UPDATE_PROJECT_QUERY, parameters);
        if (result) {
            resultProject = project;
        }
        return resultProject;
    }

    public String updateProjectStatus(Long projectId, String status) throws DaoException {
        String resultStatus = null;
        Object[] parameters = {status, projectId};
        boolean result = executor.executeUpdate(
                ConstantDaoQuery.UPDATE_PROJECT_STATUS_QUERY, parameters);
        if (result) {
            resultStatus = status;
        }
        return resultStatus;
    }

    public boolean removeProject(Long projectId, Long technicalTaskId) throws DaoException {
        String[] queries = {ConstantDaoQuery.DELETE_PROJECT_BILLS_QUERY,
                ConstantDaoQuery.DELETE_PROJECT_TASKS_QUERY,
                ConstantDaoQuery.DELETE_PROJECT_REQUIREMENTS_QUERY,
                ConstantDaoQuery.DELETE_USERS_FROM_PROJECT_QUERY,
                ConstantDaoQuery.DELETE_PROJECT_QUERY,
                ConstantDaoQuery.DELETE_PROJECT_TECHNICAL_TASKS_QUERY};
        Object[][] parameters = {{projectId}, {projectId}, {technicalTaskId},
                {projectId}, {projectId}, {technicalTaskId}};
        boolean removed = executor.executeUpdateTransactionMultiple(queries, parameters);
        return removed;
    }

}
