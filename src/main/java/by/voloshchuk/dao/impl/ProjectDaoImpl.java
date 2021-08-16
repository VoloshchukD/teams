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

    @Override
    public boolean addProject(ProjectDto projectDto) throws DaoException {
        boolean added = false;
        CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                ConstantDaoQuery.ADD_PROJECT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            Project project = projectDto.getProject();
            Object[] parameters = {project.getName(), project.getDescription(),
                    new Timestamp(project.getStartDate().getTime()), project.getState().toString(),
                    project.getTechnicalTaskId()};
            DaoExecutor.fillStatement(statement, parameters);
            added = (statement.executeUpdate() == 1);
            if (added) {
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                long projectId = resultSet.getLong(1);
                project.setId(projectId);
                try (PreparedStatement userStatement = connection.prepareStatement(
                        ConstantDaoQuery.ADD_USER_TO_PROJECT_QUERY)) {
                    parameters = new Object[]{projectId, projectDto.getCustomerId()};
                    DaoExecutor.fillStatement(userStatement, parameters);
                    userStatement.executeUpdate();
                    parameters = new Object[]{projectId, projectDto.getManagerId()};
                    DaoExecutor.fillStatement(userStatement, parameters);
                    userStatement.executeUpdate();
                }
                try (PreparedStatement taskStatement = connection.prepareStatement(
                        ConstantDaoQuery.UPDATE_TECHNICAL_TASK_STATUS_QUERY)) {
                    parameters = new Object[]{
                            TechnicalTask.TechnicalTaskStatus.ON_PROJECT.toString(),
                            project.getTechnicalTaskId()};
                    DaoExecutor.fillStatement(taskStatement, parameters);
                    taskStatement.executeUpdate();
                }
                connection.commit();
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                throw new DaoException("Exception while add project rollback ", e);
            }
            throw new DaoException("Exception while add project ", e);
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                throw new DaoException("Exception while close connection ", e);
            }
        }
        return added;
    }

    @Override
    public List<Project> findProjectsByUserIdAndState(Long userId, String state)
            throws DaoException {
        Object[] parameters = {userId, state};
        List<Project> projects = executor.executeQueryMultipleResult(
                ConstantDaoQuery.FIND_PROJECTS_BY_USER_ID_AND_STATE_QUERY, parameters);
        return projects;
    }

    @Override
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

    @Override
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

    @Override
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
